package com.example.mysandbox.dto.response;

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
    private CategoryDTO category;
    private Set<PlatformDTO> platforms;
    private Set<TagDTO> tags;

    // Nested DTO classes
    @Data
    public static class AuthorDTO {
        private Long id;
        private String username;
        private String fullName;
    }

    @Data
    public static class CategoryDTO {
        private Long id;
        private String name;
        private String slug;
    }

    @Data
    public static class PlatformDTO {
        private Long id;
        private String name;
        private String slug;
    }

    @Data
    public static class TagDTO {
        private Long id;
        private String name;
        private String slug;
    }
}
