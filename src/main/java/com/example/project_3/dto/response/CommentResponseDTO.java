package com.example.project_3.dto.response;

public record CommentResponseDTO (
     Long id,
     String content,
     Long postId,
     Long userId)
{}