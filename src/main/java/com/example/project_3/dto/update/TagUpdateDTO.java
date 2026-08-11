package com.example.project_3.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagUpdateDTO(
        @NotBlank(message = "Тег не может быть пустым!")
        @Size(min = 1, max = 255, message = "Размер тега должен быть от 1 до 255 знаков!")
        String name
) {
}
