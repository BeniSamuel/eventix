package com.evms.www.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count = 0L;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private LocalDateTime liked_at;

    public Like (Event event, User author, LocalDateTime liked_at) {
        this.count += 1;
        this.event = event;
        this.author = author;
        this.liked_at = liked_at;
    }
}
