package com.example.project_3.controller;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {
    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO dto, @AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(postService.createPost(dto, currentUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePostById(@Valid @RequestBody PostUpdateDTO dto, @PathVariable Long id, @AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(postService.updatePostById(dto,id,currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id, @AuthenticationPrincipal User currentUser){
        postService.deletePostById(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public PostResponseDTO findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }


    @GetMapping("/by-tags/{tagId}")
    public Page<PostResponseDTO> findPostsByTagId(@PathVariable Long tagId, Pageable pageable){
        return postService.findPostsByTagId(tagId,pageable);
    }


    @GetMapping("/by-users/{userId}")
    public Page<PostResponseDTO> findPostsByUserId(@PathVariable Long userId, Pageable pageable){
        return postService.findPostsByUserId(userId, pageable);
    }

}
