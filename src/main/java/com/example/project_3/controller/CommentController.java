package com.example.project_3.controller;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Validated
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}")
    public CommentResponseDTO createComment(@Valid @RequestBody CommentRequestDTO dto, @AuthenticationPrincipal User user, @PathVariable Long postId){
        return commentService.createComment(dto,user,postId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id, @AuthenticationPrincipal User currentUser){
        commentService.deleteCommentById(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public CommentResponseDTO updateComment(@Valid @RequestBody CommentUpdateDTO dto, @PathVariable Long id, @AuthenticationPrincipal User currentUser){
        return commentService.updateCommentById(dto, id, currentUser);
    }


    @GetMapping("/{id}")
    public CommentResponseDTO findCommentById(@PathVariable Long id){
        return commentService.findCommentById(id);
    }

    @GetMapping("/posts/{postId}")
    public Page<CommentResponseDTO> findCommentsByPostId(@PathVariable Long postId, Pageable pageable){
        return commentService.findCommentsByPostId(postId, pageable);
    }
}
