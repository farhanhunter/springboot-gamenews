package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.AuthRequestDTO;
import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.AuthResponseDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;
import com.example.mysandbox.security.JwtUtil;
import com.example.mysandbox.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid UserRequestDTO request) {
        try {
            UserResponseDTO user = userServiceImpl.createUser(request);
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }
}
