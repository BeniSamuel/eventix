package com.evms.www.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private String statement;
    private Long event_id;
    private Long author_id;
    private final LocalDateTime creation_date = LocalDateTime.now();
}
