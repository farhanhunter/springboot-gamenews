package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;

import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO dto);
    ArticleResponseDTO getArticle(Long id);
    ArticleResponseDTO getArticleBySlug(String slug);
    ArticleResponseDTO getArticleByCategory(ArticleResponseDTO.CategoryDTO category);
    List<ArticleResponseDTO> getAllPublishedArticles();
    ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto);
    void deleteArticle(Long id);
    List<ArticleResponseDTO> searchArticles(String query);
}
