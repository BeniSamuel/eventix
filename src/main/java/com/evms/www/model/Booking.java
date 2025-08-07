package com.evms.www.model;

import com.evms.www.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User booker;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime booked_at;

    public Booking (Event event, User booker, BookingStatus status, LocalDateTime booked_at) {
        this.event = event;
        this.booker = booker;
        this.status = status;
        this.booked_at = booked_at;
    }
}
