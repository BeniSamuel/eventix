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
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Long amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User contributor;

    private String description;

    private LocalDateTime provided_at;

    public Contribution (Event event, Long amount, User contributor, String description, LocalDateTime provided_at) {
        this.event = event;
        this.amount = amount;
        this.contributor = contributor;
        this.description = description;
        this.provided_at = provided_at;
    }
}
