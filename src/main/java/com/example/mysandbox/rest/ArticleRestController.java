package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;
import com.example.mysandbox.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/articles")
@RequiredArgsConstructor
public class ArticleRestController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> createArticle(
            @RequestBody @Valid ArticleRequestDTO request,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            ArticleResponseDTO response = articleService.createArticle(request, username);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {

            throw new RuntimeException("Failed to create article: " + e.getMessage());
        }
    }

    @GetMapping("/published")
    public ResponseEntity<List<ArticleResponseDTO>> getAllPublishedArticles() {
        try {
            List<ArticleResponseDTO> response = articleService.getAllPublishedArticles();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get published articles: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Long id) {
        try {
            ArticleResponseDTO response = articleService.getArticle(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Article not found with id: " + id);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ArticleResponseDTO> getArticleBySlug(@PathVariable String slug) {
        try {
            ArticleResponseDTO response = articleService.getArticleBySlug(slug);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Article not found with slug: " + slug);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> updateArticle(@PathVariable Long id, @RequestBody @Valid ArticleRequestDTO request) {
        try {
            ArticleResponseDTO response = articleService.updateArticle(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update article: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete article: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponseDTO>> searchArticles(@RequestParam String query) {
        try {
            List<ArticleResponseDTO> response = articleService.searchArticles(query);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search articles: " + e.getMessage());
        }
    }
}
