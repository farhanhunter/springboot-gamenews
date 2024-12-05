package com.example.mysandbox.service.impl;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;
import com.example.mysandbox.entity.*;
import com.example.mysandbox.enums.ArticleStatus;
import com.example.mysandbox.mapper.ArticleMapper;
import com.example.mysandbox.repository.*;
import com.example.mysandbox.service.ArticleService;
import com.example.mysandbox.util.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO dto, String username) {
        // 1. Validasi User
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validasi Category

        // 3. Convert ke entity
        Article article = articleMapper.toEntity(dto);

        // 4. Set data wajib
        article.setAuthor(author);
        article.setSlug(slugGenerator.generateSlug(dto.getTitle()));

        // Set categories dengan validasi
        if (dto.getCategoriesIds() != null && !dto.getCategoriesIds().isEmpty()) {
            Set<Category> categories = categoryRepository.findAllById(dto.getCategoriesIds())
                    .stream()
                    .collect(Collectors.toSet());

            if (categories.size() != dto.getCategoriesIds().size()) {
                throw new RuntimeException("One or more categories not found");
            }

            article.setCategories(categories);
        }

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

        // Set tags dengan validasi
        if (dto.getTagsIds() != null && !dto.getTagsIds().isEmpty()) {
            Set<Tag> tags = tagRepository.findAllById(dto.getTagsIds())
                    .stream()
                    .collect(Collectors.toSet());

            if (tags.size() != dto.getTagsIds().size()) {
                throw new RuntimeException("One or more tags not found");
            }

            article.setTags(tags);
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

//    @Override
//    public ArticleResponseDTO getArticleByCategory(CategoryRequestDTO category) {
//        List<Article> articles = articleRepository.findByCategoryName(category.getName());
//        if (articles.isEmpty()) {
//            throw new RuntimeException("No articles found for category: " + category.getName());
//        }
//        return articleMapper.toDto(articles.get(0));
//    }

    @Override
    public Page<ArticleResponseDTO> getAllPublishedArticles(Pageable pageable) {
        return articleRepository.findAllPublished(pageable)
                .map(articleMapper::toDto);
    }

    @Override
    @Transactional
    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Update category jika ada
        if (dto.getCategoriesIds() != null) {
            Set<Category> categories = categoryRepository.findAllById(dto.getCategoriesIds())
                    .stream()
                    .collect(Collectors.toSet());
            if (!dto.getCategoriesIds().isEmpty() && categories.size() != dto.getCategoriesIds().size()) {
                throw new RuntimeException("One or more categories not found");
            }
            existingArticle.setCategories(categories);
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

        // Update tags jika ada
        if (dto.getTagsIds() != null) {
            Set<Tag> tags = tagRepository.findAllById(dto.getTagsIds())
                    .stream()
                    .collect(Collectors.toSet());
            if (!dto.getTagsIds().isEmpty() && tags.size() != dto.getTagsIds().size()) {
                throw new RuntimeException("One or more tags not found");
            }
            existingArticle.setTags(tags);
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
