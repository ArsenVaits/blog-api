package com.example.project_3.contoller;

import com.example.project_3.dto.request.TagRequestDTO;
import com.example.project_3.dto.response.TagResponseDTO;
import com.example.project_3.dto.update.TagUpdateDTO;
import com.example.project_3.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Validated
public class TagController {
    private final TagService tagService;



    @PostMapping
    public TagResponseDTO createTag(@Valid  @RequestBody TagRequestDTO dto){
        return tagService.createTag(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id){
        return tagService.deleteTagById(id);
    }

    @PatchMapping("/{id}")
    public TagResponseDTO updateTagById(@Valid @RequestBody TagUpdateDTO dto, @PathVariable Long id){
        return tagService.updateTagById(dto, id);
    }

    @GetMapping("/{id}")
    public TagResponseDTO findTagById(@PathVariable Long id){
        return tagService.findTagById(id);
    }

    @GetMapping("/posts/{postId}")
    public Page<TagResponseDTO> getTagsByPostId(@PathVariable Long postId, Pageable pageable){
        return tagService.getTagsByPostId(postId, pageable);
    }

}
