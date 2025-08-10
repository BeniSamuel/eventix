package com.evms.www.controller;

import com.evms.www.dto.BookingDto;
import com.evms.www.enums.BookingStatus;
import com.evms.www.model.Booking;
import com.evms.www.service.EventBookingService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/booking")
@AllArgsConstructor
public class EventBookingController {
    private final EventBookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings () {
        return ApiResponse.ok("Successfully obtained all event bookings!!! 🎉🎉🎉", this.bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById (@PathVariable Long id) {
        Booking booking = this.bookingService.getBookingById(id);
        if (booking != null) {
            return ApiResponse.ok("Successfully obtained an event booking!!! 🎉🎉🎉", booking);
        }
        return ApiResponse.notFound("Failed to obtain event booking not found!!! 💔😔😔", null);
    }

    @GetMapping("/booking-status/{status}")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingByStatus (@PathVariable BookingStatus status) {
        return ApiResponse.ok("Successfully obtained all event with status {" + status + "}!!! 🎉🎉🎉", this.bookingService.getBookingByStatus(status));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Booking>> createBooking (@RequestBody BookingDto bookingDto) {
        Booking newBooking = this.bookingService.createBooking(bookingDto);
        if (newBooking != null) {
            return ApiResponse.created("Successfully created a new booking!!! 🎉🎉🎉", newBooking);
        }
        return ApiResponse.badRequest("Failed to create a new booking bad request!!! 💔😔😔", null);
    }
}
