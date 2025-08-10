package com.evms.www.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContributionDto {
    private Long event_id;
    private Long amount;
    private Long contributor_id;
    private String description;
    private final LocalDateTime provided_at = LocalDateTime.now();
}
