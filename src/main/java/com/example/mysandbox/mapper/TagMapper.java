package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.TagRequestDTO;
import com.example.mysandbox.dto.response.TagResponseDTO;
import com.example.mysandbox.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Tag toEntity(TagRequestDTO dto);

    TagResponseDTO toDto(Tag entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(TagRequestDTO dto, @MappingTarget Tag tag);
}
