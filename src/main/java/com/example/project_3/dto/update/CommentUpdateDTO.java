package com.example.project_3.dto.update;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDTO(
        @NotBlank(message = "Комментарий не может быть пустым!")
        String content
) {
}
