package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;
import com.example.mysandbox.enums.UserStatus;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO getUser(Long id);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllUsers();
    List<UserResponseDTO> getUsersByStatus(UserStatus status); // tambahan
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
    UserResponseDTO updateUserStatus(Long id, UserStatus status); // tambahan
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
