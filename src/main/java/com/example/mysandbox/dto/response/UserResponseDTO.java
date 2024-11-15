package com.example.mysandbox.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
