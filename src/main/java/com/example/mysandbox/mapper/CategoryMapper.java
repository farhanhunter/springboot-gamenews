package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.CategoryRequestDTO;
import com.example.mysandbox.dto.response.CategoryResponseDTO;
import com.example.mysandbox.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toDto(Category entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(CategoryRequestDTO dto, @MappingTarget Category entity);
}