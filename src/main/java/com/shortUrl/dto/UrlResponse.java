package com.shortUrl.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UrlResponse(
        Long id,
        String urlBase,
        String shortUrl,
        Long clicks,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
