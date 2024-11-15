package com.example.mysandbox.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDTO {
    @NotNull(message = "Article ID is mandatory")
    private Long articleId;

    private Long parentId;

    @NotBlank(message = "Content is mandatory")
    private String content;
}
