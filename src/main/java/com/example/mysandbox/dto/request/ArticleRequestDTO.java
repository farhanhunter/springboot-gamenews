package com.example.mysandbox.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class ArticleRequestDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Content is mandatory")
    private String content;

    private String excerpt;

    private String featuredImage;

    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    private Set<Long> platformsIds;

    private Set<Long> tagsIds;

    private String status = "draft";
}
