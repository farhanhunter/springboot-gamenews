package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findBySlug(String slug);
    List<Tag> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
}
