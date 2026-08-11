package com.example.project_3.dto.response;

public record PostResponseDTO(
        Long id,
        String title,
        String content,
        Long userId
) {
}
