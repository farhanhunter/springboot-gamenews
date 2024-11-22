package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Category;
import com.example.mysandbox.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(CategoryType name);
    Optional<Category> findBySlug(String slug);
}
