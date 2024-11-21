package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.response.TagResponseDTO;
import com.example.mysandbox.enums.TagType;
import com.example.mysandbox.mapper.TagMapper;
import com.example.mysandbox.repository.TagRepository;
import com.example.mysandbox.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagResponseDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagResponseDTO getTag(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public TagResponseDTO getTagBySlug(String slug) {
        return tagRepository.findBySlug(slug)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public List<TagType> getAllTagTypes() {
        return Arrays.asList(TagType.values());
    }
}
