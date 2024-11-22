package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.CategoryResponseDTO;
import com.example.mysandbox.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        try {
            List<CategoryResponseDTO> response = categoryService.getAllCategories();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get categories: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Long id) {
        try {
            CategoryResponseDTO response = categoryService.getCategory(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<CategoryResponseDTO> getCategoryBySlug(@PathVariable String slug) {
        try {
            CategoryResponseDTO response = categoryService.getCategoryBySlug(slug);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Category not found with slug: " + slug);
        }
    }

}
