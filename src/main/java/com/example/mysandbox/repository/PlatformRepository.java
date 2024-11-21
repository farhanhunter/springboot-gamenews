package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Platform;
import com.example.mysandbox.enums.PlatformType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    boolean existsByName(PlatformType name);
    Optional<Platform> findBySlug(String slug);
}
