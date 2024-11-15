package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findBySlug(String slug);
    boolean existsByName(String name);
}
