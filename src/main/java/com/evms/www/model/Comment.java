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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statement;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private LocalDateTime created_at;

    public Comment (String statement, Event event, User author, LocalDateTime created_at) {
        this.statement = statement;
        this.event = event;
        this.author = author;
        this.created_at = created_at;
    }
}
