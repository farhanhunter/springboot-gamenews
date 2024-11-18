package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategory(Long id);
    CategoryResponseDTO getCategoryBySlug(String slug);
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto);
    void deleteCategory(Long id);
}
