package com.evms.www.repository;

import com.evms.www.enums.BookingStatus;
import com.evms.www.model.Booking;
import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getBookingsByBooker (User booker);
    List<Booking> getBookingsByStatus (BookingStatus status);
}
