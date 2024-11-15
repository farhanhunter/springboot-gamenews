package com.example.mysandbox.service;

import com.example.mysandbox.dto.request.MediaRequestDTO;
import com.example.mysandbox.dto.response.MediaResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponseDTO uploadMedia(MultipartFile file, MediaRequestDTO dto);
    MediaResponseDTO getMedia(Long id);
    List<MediaResponseDTO> getMediaByArticleId(Long articleId);
    void deleteMedia(Long id);
}
