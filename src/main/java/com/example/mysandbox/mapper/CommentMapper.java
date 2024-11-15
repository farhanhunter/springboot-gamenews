package com.example.mysandbox.mapper;

import com.example.mysandbox.dto.request.CommentRequestDTO;
import com.example.mysandbox.dto.response.CommentResponseDTO;
import com.example.mysandbox.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntity(CommentRequestDTO dto);

    CommentResponseDTO toDto(Comment entity);
}
