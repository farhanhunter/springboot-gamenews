package com.example.mysandbox.rest;

import com.example.mysandbox.dto.response.TagResponseDTO;
import com.example.mysandbox.service.TagService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game/tags")
@RequiredArgsConstructor
public class TagRestController {
    private final TagService tagService;
    private static final Logger LOG = LoggerFactory.getLogger(TagRestController.class);

    @GetMapping("/types")
    public ResponseEntity<List<Map<String, String>>> getAllTagTypes() {
        return ResponseEntity.ok(
                tagService.getAllTagTypes().stream()
                        .map(type -> Map.of(
                                "name", type.name(),
                                "displayName", type.getDisplayName()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> getTag(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTag(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<TagResponseDTO> getTagBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(tagService.getTagBySlug(slug));
    }
}