package com.example.project_3.controller;

import com.example.project_3.dto.request.TagRequestDTO;
import com.example.project_3.dto.response.TagResponseDTO;
import com.example.project_3.dto.update.TagUpdateDTO;
import com.example.project_3.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Validated
public class TagController {
    private final TagService tagService;


    @PostMapping
    public TagResponseDTO createTag(@Valid @RequestBody TagRequestDTO dto) {
        return tagService.createTag(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id) {
        tagService.deleteTagById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public TagResponseDTO updateTagById(@Valid @RequestBody TagUpdateDTO dto, @PathVariable Long id) {
        return tagService.updateTagById(dto, id);
    }

    @GetMapping("/{id}")
    public TagResponseDTO findTagById(@PathVariable Long id) {
        return tagService.findTagById(id);
    }

    @GetMapping("/by-posts/{postId}")
    public Page<TagResponseDTO> getTagsByPostId(@PathVariable Long postId, Pageable pageable) {
        return tagService.getTagsByPostId(postId, pageable);
    }

}
