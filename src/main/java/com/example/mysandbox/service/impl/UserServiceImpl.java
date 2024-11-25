package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;
import com.example.mysandbox.entity.User;
import com.example.mysandbox.enums.UserStatus;
import com.example.mysandbox.exception.UserAlreadyExistsException;
import com.example.mysandbox.exception.UserNotFoundException;
import com.example.mysandbox.mapper.UserMapper;
import com.example.mysandbox.repository.UserRepository;
import com.example.mysandbox.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        log.info("Creating new user with username: {}", dto.getUsername());
        validateNewUser(dto);

        User user = userMapper.toEntity(dto);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        // Set timestamps manually if needed
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User savedUser = userRepository.save(user);
        log.info("Successfully created user with ID: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }

    private void validateNewUser(UserRequestDTO dto) {
        if (existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + dto.getUsername());
        }
        if (existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + dto.getEmail());
        }
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getUsersByStatus(UserStatus status) {
        return userRepository.findByStatus(status).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        log.info("Updating user with ID: {}", id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        validateUserUpdate(dto, existingUser);
        userMapper.updateEntity(dto, existingUser);

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }

        User updated = userRepository.save(existingUser);
        log.info("Successfully updated user with ID: {}", id);
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUserStatus(Long id, UserStatus status) {
        log.info("Updating status to {} for user ID: {}", status, id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setStatus(status);
        User updated = userRepository.save(user);
        log.info("Successfully updated status for user ID: {}", id);
        return userMapper.toDto(updated);
    }

    private void validateUserUpdate(UserRequestDTO dto, User existingUser) {
        if (!existingUser.getUsername().equals(dto.getUsername()) && existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists: " + dto.getUsername());
        }
        if (!existingUser.getEmail().equals(dto.getEmail()) && existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + dto.getEmail());
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("Successfully deleted user with ID: {}", id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
