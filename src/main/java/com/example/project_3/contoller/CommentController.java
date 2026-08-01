package com.example.project_3.contoller;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Validated
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/users/{userId}/posts/{postId}")
    public CommentResponseDTO createComment(@Valid @RequestBody CommentRequestDTO dto, @PathVariable Long userId, @PathVariable Long postId){
        return commentService.createComment(dto,userId,postId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id){
        return commentService.deleteCommentById(id);
    }

    @PatchMapping("/{id}")
    public CommentResponseDTO updateComment(@Valid @RequestBody CommentUpdateDTO dto, @PathVariable Long id){
        return commentService.updateCommentById(dto, id);
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
