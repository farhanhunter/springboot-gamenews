package com.example.mysandbox.service.impl;

import com.example.mysandbox.entity.Category;
import com.example.mysandbox.enums.CategoryType;
import com.example.mysandbox.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CategoryInitializer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryInitializer.class);
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        LOG.debug("Initializing categories...");
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryType.values())
                    .forEach(categoryType -> {
                        Category category = new Category();
                        category.setName(categoryType);
                        Category saved = categoryRepository.save(category);
                        LOG.debug("Created category: {}", saved);
                    });
        }
    }
}
