package com.example.mysandbox.dto.request;

import com.example.mysandbox.enums.ArticleStatus;
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
    private Set<Long> categoriesIds;
    private Set<Long> platformsIds;
    private Set<Long> tagsIds;
    @NotNull(message = "Status is mandatory")
    private ArticleStatus status = ArticleStatus.DRAFT;
}
