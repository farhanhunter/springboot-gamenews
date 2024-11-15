package com.example.mysandbox.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MediaRequestDTO {
    @NotNull(message = "File is required")
    private MultipartFile file;

    private Long articleId;

    private String altText;
}
