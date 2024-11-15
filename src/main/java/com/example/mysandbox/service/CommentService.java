package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.CommentRequestDTO;
import com.example.mysandbox.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    CommentResponseDTO createComment(CommentRequestDTO dto);
    CommentResponseDTO getComment(Long id);
    List<CommentResponseDTO> getCommentsByArticleId(Long articleId);
    List<CommentResponseDTO> getRootCommentsByArticleId(Long articleId);
    List<CommentResponseDTO> getReplies(Long parentId);
    CommentResponseDTO updateComment(Long id, CommentRequestDTO dto);
    void deleteComment(Long id);
}
