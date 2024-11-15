package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByArticleId(Long articleId);
}
