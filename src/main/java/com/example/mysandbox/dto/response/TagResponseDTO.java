package com.example.mysandbox.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private String displayName;
    private LocalDateTime createdAt;
}
