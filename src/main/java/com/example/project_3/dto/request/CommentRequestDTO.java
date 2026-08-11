package com.example.project_3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CommentRequestDTO(
        @NotBlank(message = "Комментарий не может быть пустой")
        @Size(max = 255, message = "Комментарий не должен превышать 255 символов")
        String content,
        @NotNull(message = "Комментарий обязательно должен иметь userId!")
        Long userId,
        @NotNull(message = "Комментарий обязательно должен иметь postId!")
        Long postId
) {
}
