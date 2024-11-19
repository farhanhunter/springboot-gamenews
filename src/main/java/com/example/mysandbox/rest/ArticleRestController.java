package com.example.mysandbox.rest;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
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

        String username = userDetails.getUsername();
        ArticleResponseDTO response = articleService.createArticle(request, username);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/published")
    public ResponseEntity<List<ArticleResponseDTO>> getAllPublishedArticles() {
        List<ArticleResponseDTO> response = articleService.getAllPublishedArticles();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@PathVariable Long id) {
        try {
            ArticleResponseDTO response = articleService.getArticle(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ArticleResponseDTO> getArticleBySlug(@PathVariable String slug) {
        try {
            ArticleResponseDTO response = articleService.getArticleBySlug(slug);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category")
    public ResponseEntity<ArticleResponseDTO> getArticleByCategory(@RequestBody @Valid ArticleResponseDTO.CategoryDTO request) {
        try {
            ArticleResponseDTO response = articleService.getArticleByCategory(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> updateArticle(@PathVariable Long id, @RequestBody @Valid ArticleRequestDTO request) {
        try {
            ArticleResponseDTO response = articleService.updateArticle(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponseDTO>> searchArticles(@RequestParam String query) {
        List<ArticleResponseDTO> response = articleService.searchArticles(query);
        return ResponseEntity.ok(response);
    }
}
