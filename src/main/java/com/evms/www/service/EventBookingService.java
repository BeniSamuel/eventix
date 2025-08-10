package com.evms.www.service;

import com.evms.www.dto.BookingDto;
import com.evms.www.enums.BookingStatus;
import com.evms.www.enums.Status;
import com.evms.www.model.Booking;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import com.evms.www.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventBookingService {
    private final EventService eventService;
    private final UserService userService;
    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings () {
        return bookingRepository.findAll();
    }

    public Booking getBookingById (Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getBookingByBooker (Long user_id) {
        User booker = userService.getUserById(user_id);
        if (booker == null) {
            return null;
        }

        return bookingRepository.getBookingsByBooker(booker);
    }

    public List<Booking> getBookingByStatus (BookingStatus status) {
        return bookingRepository.getBookingsByStatus(status);
    }

    public Booking createBooking (BookingDto bookingDto) {
        Event event = eventService.getEventById(bookingDto.getEvent_id());
        if (event == null) {
            return null;
        }

        User booker = userService.getUserById(bookingDto.getUser_id());
        if (booker == null) {
            return null;
        }

        if (event.getStatus().equals(Status.OPENED)) {
            Booking booking = new Booking(event, booker, BookingStatus.PENDING, bookingDto.getBooked_at());
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking approveBooking (Long booking_id) {
        Booking booking = getBookingById(booking_id);
        if (booking != null && booking.getStatus().equals(BookingStatus.PENDING)) {
            booking.setStatus(BookingStatus.APPROVED);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking updateBooking (Long booking_id, BookingDto bookingDto) {
        Booking booking = getBookingById(booking_id);
        if (booking == null) {
            return null;
        }

        User booker = userService.getUserById(bookingDto.getUser_id());
        if (booker == null) {
            return null;
        }

        Event event = eventService.getEventById(bookingDto.getEvent_id());
        if (event == null) {
            return null;
        }

        if (booking.getStatus().equals(BookingStatus.APPROVED) && event.getStatus().equals(Status.CLOSED)) {
            return null;
        }

        booking.setEvent(event);
        booking.setBooker(booker);
        booking.setBooked_at(bookingDto.getBooked_at());
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    public Boolean deleteBooking (Long id) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            bookingRepository.delete(booking);
            return true;
        }
        return false;
    }
}
