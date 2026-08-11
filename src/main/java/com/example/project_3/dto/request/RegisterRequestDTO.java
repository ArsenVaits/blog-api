package com.example.project_3.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Поле username не может быть пустым!")
        @Size(min = 3, max = 30, message = "Username должен иметь от 3 до 30 символов!")
        String username,
        @NotBlank(message = "Поле password не может быть пустым!")
        @Size(min = 8, max = 30, message = "Максимальная длина пароля может быть от 8 до 30 символов!")
        String password,
        @Email(message = "Неверный формат email!")
        @NotBlank(message = "Email не может быть пустым!")
        String email
) {
}
