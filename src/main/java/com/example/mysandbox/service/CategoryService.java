package com.example.mysandbox.service;

import com.example.mysandbox.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategory(Long id);
    CategoryResponseDTO getCategoryBySlug(String slug);
}
