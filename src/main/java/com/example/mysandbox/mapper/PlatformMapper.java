package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.PlatformRequestDTO;
import com.example.mysandbox.dto.response.PlatformResponseDTO;
import com.example.mysandbox.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Platform toEntity(PlatformRequestDTO dto);

    PlatformResponseDTO toDto(Platform entity);
}
