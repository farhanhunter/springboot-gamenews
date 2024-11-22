package com.example.mysandbox.repository;

import com.example.mysandbox.entity.Article;
import com.example.mysandbox.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findBySlug(String slug);

//    List<Article> findByCategoryName(CategoryType name);

    @Query("SELECT a FROM Article a WHERE a.status = 'PUBLISHED' ORDER BY a.publishedAt DESC")
    List<Article> findAllPublished();

//    @Query("SELECT a FROM Article a WHERE a.status = 'PUBLISHED' AND" +
//            "(:categoryId IS NULL OR a.category.id = :categoryId) AND " +
//            "(:platformId IS NULL OR :platformId IN (SELECT p.id FROM a.platforms p)) AND " +
//            "(:tagId IS NULL OR :tagId IN (SELECT t.id FROM a.tags t))")
//    List<Article> findArticlesWithFilters(
//            @Param("categoryId") Long categoryId,
//            @Param("platformId") Long platformId,
//            @Param("tagId") Long tagId
//    );

    @Query(value = "SELECT * FROM articles WHERE to_tsvector('english', title || ' ' || content) @@ plainto_tsquery('english', :query)",
            nativeQuery = true)
    List<Article> searchArticles(@Param("query") String query);
}
