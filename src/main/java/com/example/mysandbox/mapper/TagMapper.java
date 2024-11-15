package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.TagRequestDTO;
import com.example.mysandbox.dto.response.TagResponseDTO;
import com.example.mysandbox.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Tag toEntity(TagRequestDTO dto);

    TagResponseDTO toDto(Tag entity);
}
