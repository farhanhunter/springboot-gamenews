package com.example.mysandbox.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MediaResponseDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private String altText;
    private Long articleId;
    private LocalDateTime createdAt;
}
