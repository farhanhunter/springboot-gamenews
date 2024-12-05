package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findBySlug(String slug);

    @Query("SELECT a FROM Article a WHERE a.status = 'PUBLISHED' ORDER BY a.publishedAt DESC")
    Page<Article> findAllPublished(Pageable pageable);

    @Query(value = "SELECT * FROM articles WHERE to_tsvector('english', title || ' ' || content) @@ plainto_tsquery('english', :query)",
            nativeQuery = true)
    List<Article> searchArticles(@Param("query") String query);
}