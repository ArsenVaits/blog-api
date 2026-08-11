package com.example.project_3.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO (
        @NotBlank(message = "username не может быть пустым!")
        String username,
        @NotBlank(message = "password не может быть пустым!")
        String password
){}
