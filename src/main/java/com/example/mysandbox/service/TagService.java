package com.example.mysandbox.service;

import com.example.mysandbox.dto.response.TagResponseDTO;
import com.example.mysandbox.enums.TagType;

import java.util.List;

public interface TagService {
    List<TagResponseDTO> getAllTags();
    TagResponseDTO getTag(Long id);
    TagResponseDTO getTagBySlug(String slug);
    List<TagType> getAllTagTypes();
}
