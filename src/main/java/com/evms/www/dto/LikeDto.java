package com.evms.www.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeDto {
    private Long event_id;
    private Long author_id;
    private final LocalDateTime liked_at = LocalDateTime.now();
}
