package com.example.project_3.dto.update;

import jakarta.validation.constraints.Size;

import java.util.List;

public record PostUpdateDTO(
        @Size(min = 5, max = 255, message = "Заголовок поста должен быть от 5 до 255 знаков!")
        String title,
        String content,
        List<Long> tagIds
) {
    public PostUpdateDTO {
        if (tagIds == null) {
            tagIds = List.of();
        }
    }
}
