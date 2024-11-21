package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;
import com.example.mysandbox.entity.Article;
import com.example.mysandbox.entity.Category;
import com.example.mysandbox.entity.Platform;
import com.example.mysandbox.entity.User;
import com.example.mysandbox.enums.ArticleStatus;
import com.example.mysandbox.mapper.ArticleMapper;
import com.example.mysandbox.repository.ArticleRepository;
import com.example.mysandbox.repository.CategoryRepository;
import com.example.mysandbox.repository.PlatformRepository;
import com.example.mysandbox.repository.UserRepository;
import com.example.mysandbox.service.ArticleService;
import com.example.mysandbox.util.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;
    private final SlugGenerator slugGenerator;
    private final CategoryRepository categoryRepository;
    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO dto, String username) {
        // 1. Validasi User
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validasi Category
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 3. Convert ke entity
        Article article = articleMapper.toEntity(dto);

        // 4. Set data wajib
        article.setCategory(category);
        article.setAuthor(author);
        article.setSlug(slugGenerator.generateSlug(dto.getTitle()));

        // Set platforms dengan validasi
        if (dto.getPlatformsIds() != null && !dto.getPlatformsIds().isEmpty()) {
            Set<Platform> platforms = platformRepository.findAllById(dto.getPlatformsIds())
                    .stream()
                    .collect(Collectors.toSet());

            // Validasi apakah semua platform ditemukan
            if (platforms.size() != dto.getPlatformsIds().size()) {
                throw new RuntimeException("One or more platforms not found");
            }

            article.setPlatforms(platforms);
        }

        // 6. Handle status dan publishedAt
        if (ArticleStatus.PUBLISHED.equals(dto.getStatus())) {
            article.setPublishedAt(LocalDateTime.now());
        }

        // 7. Save dan return
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    @Override
    public ArticleResponseDTO getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleResponseDTO getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Article not found"));
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleResponseDTO getArticleByCategory(ArticleResponseDTO.CategoryDTO category) {
        List<Article> articles = articleRepository.findByCategoryId(category.getId());
        if (articles.isEmpty()) {
            throw new RuntimeException("No articles found for category: " + category.getName());
        }
        return articleMapper.toDto(articles.get(0));
    }

    @Override
    public List<ArticleResponseDTO> getAllPublishedArticles() {
        return articleRepository.findAllPublished()
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Update category jika ada
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingArticle.setCategory(category);
        }

        // Update platforms jika ada
        if (dto.getPlatformsIds() != null) {
            Set<Platform> platforms = platformRepository.findAllById(dto.getPlatformsIds())
                    .stream()
                    .collect(Collectors.toSet());
            if (!dto.getPlatformsIds().isEmpty() && platforms.size() != dto.getPlatformsIds().size()) {
                throw new RuntimeException("One or more platforms not found");
            }
            existingArticle.setPlatforms(platforms);
        }

        // Update basic info
        articleMapper.updateEntity(dto, existingArticle);

        // Set publishedAt jika berubah ke PUBLISHED
        if (ArticleStatus.PUBLISHED.equals(dto.getStatus()) &&
                existingArticle.getPublishedAt() == null) {
            existingArticle.setPublishedAt(LocalDateTime.now());
        }

        Article updatedArticle = articleRepository.save(existingArticle);
        return articleMapper.toDto(updatedArticle);
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleResponseDTO> searchArticles(String query) {
        return articleRepository.searchArticles(query).stream().map(articleMapper::toDto).collect(Collectors.toList());
    }
}
