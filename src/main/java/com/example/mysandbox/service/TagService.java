package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.TagRequestDTO;
import com.example.mysandbox.dto.response.TagResponseDTO;

import java.util.List;

public interface TagService {
    TagResponseDTO createTag(TagRequestDTO dto);
    TagResponseDTO getTag(Long id);
    TagResponseDTO getTagBySlug(String slug);
    List<TagResponseDTO> getAllTags();
    List<TagResponseDTO> searchTags(String name);
    TagResponseDTO updateTag(Long id, TagRequestDTO dto);
    void deleteTag(Long id);
}
