package com.evms.www.model;

import com.evms.www.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    private float rating;

    private String overview;

    private LocalDateTime start_time;

    private LocalDateTime end_time;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Event (String name, String description, User creator, String location, String overview, LocalDateTime start_time, LocalDateTime end_time, Status event_status) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.location = location;
        this.overview = overview;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = event_status;
    }
}
