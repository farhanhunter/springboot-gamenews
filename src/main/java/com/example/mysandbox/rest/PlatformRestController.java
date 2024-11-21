package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.response.PlatformResponseDTO;
import com.example.mysandbox.enums.PlatformType;
import com.example.mysandbox.service.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game/platforms")
@RequiredArgsConstructor
public class PlatformRestController {
    private final PlatformService platformService;

    // Get all available platform types (dari enum)
    @GetMapping("/types")
    public ResponseEntity<List<Map<String, String>>> getAllPlatformTypes() {
        return ResponseEntity.ok(
                Arrays.stream(PlatformType.values())
                        .map(type -> Map.of(
                                "name", type.name(),
                                "displayName", type.getDisplayName()
                        ))
                        .collect(Collectors.toList())
        );
    }

    // Get all platforms dari database
    @GetMapping
    public ResponseEntity<List<PlatformResponseDTO>> getAllPlatforms() {
        return ResponseEntity.ok(platformService.getAllPlatforms());
    }

    // Get platform by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponseDTO> getPlatform(@PathVariable Long id) {
        return ResponseEntity.ok(platformService.getPlatform(id));
    }

    // Get platform by slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity<PlatformResponseDTO> getPlatformBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(platformService.getPlatformBySlug(slug));
    }
}