package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO dto, String username);
    ArticleResponseDTO getArticle(Long id);
    ArticleResponseDTO getArticleBySlug(String slug);
    Page<ArticleResponseDTO> getAllPublishedArticles(Pageable pageable);
    ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto);
    void deleteArticle(Long id);
    List<ArticleResponseDTO> searchArticles(String query);
}