package com.evms.www.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingDto {
    private Long event_id;
    private Long user_id;
    private final LocalDateTime booked_at = LocalDateTime.now();
}
