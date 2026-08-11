package com.example.project_3.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(min = 3, max = 55, message = "Имя должно быть от 3  до 55 символов символов")
        String username,
        @Email(message = "Email имеет неверный формат!")
        String email
) {
}
