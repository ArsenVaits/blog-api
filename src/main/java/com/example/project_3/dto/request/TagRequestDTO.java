package com.example.project_3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagRequestDTO(
        @NotBlank(message = "Тег не может быть пуст!")
        @Size(min = 1, max = 255, message = "Размер тега должен быть от 1 до 255 символов!")
        String name
) {
}
