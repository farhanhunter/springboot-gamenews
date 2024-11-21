package com.example.mysandbox.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PlatformResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private String displayName;
    private LocalDateTime createdAt;
}