package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    // Membuat user baru
    UserResponseDTO createUser(UserRequestDTO dto);

    // Mendapatkan user berdasarkan ID
    UserResponseDTO getUser(Long id);

    // Mencari user berdasarkan username
    UserResponseDTO getUserByUsername(String username);

    // Mencari user berdasarkan email
    UserResponseDTO getUserByEmail(String email);

    // Mendapatkan semua user
    List<UserResponseDTO> getAllUsers();

    // Update data user berdasarkan ID
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

    // Menghapus user berdasarkan ID
    void deleteUser(Long id);

    // Cek apakah username sudah ada
    boolean existsByUsername(String username);

    // Cek apakah email sudah ada
    boolean existsByEmail(String email);
}
