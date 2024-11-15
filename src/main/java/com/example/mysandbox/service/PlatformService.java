package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.response.PlatformResponseDTO;

import java.util.List;

public interface PlatformService {
    PlatformResponseDTO createPlatform(PlatformRequestDTO dto);
    PlatformResponseDTO getPlatform(Long id);
    PlatformResponseDTO getPlatformBySlug(String slug);
    List<PlatformResponseDTO> getAllPlatforms();
    PlatformResponseDTO updatePlatform(Long id, PlatformRequestDTO dto);
    void deletePlatform(Long id);
}
