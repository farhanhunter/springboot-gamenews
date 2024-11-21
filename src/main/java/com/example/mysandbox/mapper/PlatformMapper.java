package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.response.PlatformResponseDTO;
import com.example.mysandbox.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Platform toEntity(PlatformRequestDTO dto);

    PlatformResponseDTO toDto(Platform entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(PlatformRequestDTO dto, @MappingTarget Platform platform);
}