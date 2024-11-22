package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Platform;
import com.example.mysandbox.enums.PlatformType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    boolean existsByName(PlatformType name);
    Optional<Platform> findBySlug(String slug);
}
