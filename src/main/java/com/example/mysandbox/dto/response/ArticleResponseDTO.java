package com.example.mysandbox.dto.response;

import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.request.TagRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String excerpt;
    private String featuredImage;
    private String status;
    private Integer viewCount;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Nested DTOs for related entities
    private AuthorDTO author;
    private Set<CategoryRequestDTO> categories;
    private Set<PlatformRequestDTO> platforms;
    private Set<TagRequestDTO> tags;

    // Nested DTO classes
    @Data
    public static class AuthorDTO {
        private Long id;
        private String username;
        private String fullName;
    }
}
