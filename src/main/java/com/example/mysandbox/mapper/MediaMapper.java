package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.MediaRequestDTO;
import com.example.mysandbox.dto.response.MediaResponseDTO;
import com.example.mysandbox.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Media toEntity(MediaRequestDTO dto);

    MediaResponseDTO toDto(Media entity);
}
