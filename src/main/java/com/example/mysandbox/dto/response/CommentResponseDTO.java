package com.example.mysandbox.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserDTO user;
    private Long parentId;
    private Long articleId;

    @Data
    public static class UserDTO {
        private Long id;
        private String username;
        private String fullName;
    }
}
