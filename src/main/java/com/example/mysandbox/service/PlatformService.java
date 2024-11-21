package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.response.PlatformResponseDTO;

import java.util.List;

public interface PlatformService {
    List<PlatformResponseDTO> getAllPlatforms();
    PlatformResponseDTO getPlatform(Long id);
    PlatformResponseDTO getPlatformBySlug(String slug);
}
