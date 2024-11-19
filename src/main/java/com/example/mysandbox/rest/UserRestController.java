package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;
import com.example.mysandbox.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO request) {
        try {
            UserResponseDTO response = userService.createUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        try {
            UserResponseDTO response = userService.getUser(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        try {
            UserResponseDTO response = userService.getUserByUsername(username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("User not found with username: " + username);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserResponseDTO response = userService.getUserByEmail(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        try {
            List<UserResponseDTO> response = userService.getAllUsers();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequestDTO request
    ) {
        try {
            UserResponseDTO response = userService.updateUser(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        try {
            boolean exists = userService.existsByUsername(username);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if user exists: " + e.getMessage());
        }
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        try {
            boolean exists = userService.existsByEmail(email);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if user exists: " + e.getMessage());
        }
    }
}
