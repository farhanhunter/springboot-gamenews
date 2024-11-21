package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.ArticleRequestDTO;
import com.example.mysandbox.dto.response.ArticleResponseDTO;
import com.example.mysandbox.entity.Article;
import com.example.mysandbox.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, PlatformMapper.class})
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "publishedAt", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "platforms", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Article toEntity(ArticleRequestDTO dto);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "platforms", source = "platforms")
    @Mapping(target = "tags", source = "tags")
    ArticleResponseDTO toDto(Article entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "platforms", ignore = true)
    @Mapping(target = "tags", ignore = true)
    void updateEntity(ArticleRequestDTO dto, @MappingTarget Article entity);
}