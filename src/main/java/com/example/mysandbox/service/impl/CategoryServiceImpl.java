package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.CategoryResponseDTO;
import com.example.mysandbox.entity.Category;
import com.example.mysandbox.mapper.CategoryMapper;
import com.example.mysandbox.repository.CategoryRepository;
import com.example.mysandbox.service.CategoryService;
import com.example.mysandbox.util.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final SlugGenerator slugGenerator;


    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.toEntity(dto);
        category.setSlug(slugGenerator.generateSlug(dto.getName()));
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getName().equals(dto.getName()) && categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists");
        }

        categoryMapper.updateEntity(dto, category);
        category.setSlug(slugGenerator.generateSlug(dto.getName()));
        Category updated = categoryRepository.save(category);
        return categoryMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
