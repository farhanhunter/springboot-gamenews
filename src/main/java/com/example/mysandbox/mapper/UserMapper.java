package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.UserRequestDTO;
import com.example.mysandbox.dto.response.UserResponseDTO;
import com.example.mysandbox.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)  // Password akan di-handle di service
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDto(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    void updateEntity(UserRequestDTO dto, @MappingTarget User entity);
}