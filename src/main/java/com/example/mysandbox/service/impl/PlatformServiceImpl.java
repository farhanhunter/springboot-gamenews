package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.response.PlatformResponseDTO;
import com.example.mysandbox.mapper.PlatformMapper;
import com.example.mysandbox.repository.PlatformRepository;
import com.example.mysandbox.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    @Override
    public List<PlatformResponseDTO> getAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(platformMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlatformResponseDTO getPlatform(Long id) {
        return platformRepository.findById(id)
                .map(platformMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Platform not found"));
    }

    @Override
    public PlatformResponseDTO getPlatformBySlug(String slug) {
        return platformRepository.findBySlug(slug)
                .map(platformMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Platform not found"));
    }
}