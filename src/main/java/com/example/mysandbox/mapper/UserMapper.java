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
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", constant = "PENDING_VERIFICATION")
    @Mapping(target = "articles", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDto(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "articles", ignore = true)
    void updateEntity(UserRequestDTO dto, @MappingTarget User entity);
}