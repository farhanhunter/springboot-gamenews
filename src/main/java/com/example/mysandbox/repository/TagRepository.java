package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Tag;
import com.example.mysandbox.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findBySlug(String slug);
    boolean existsByName(TagType name);
}
