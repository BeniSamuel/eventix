package com.evms.www.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventDto {
    private String name;
    private String description;
    private String location;
    private Long creator_id;
    private float rating;
    private String overview;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
}
