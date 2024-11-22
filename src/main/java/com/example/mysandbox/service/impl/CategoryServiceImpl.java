package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.response.CategoryResponseDTO;
import com.example.mysandbox.mapper.CategoryMapper;
import com.example.mysandbox.repository.CategoryRepository;
import com.example.mysandbox.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryResponseDTO getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
