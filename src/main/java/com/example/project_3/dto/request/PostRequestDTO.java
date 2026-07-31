package com.example.project_3.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PostRequestDTO {
    @NotBlank(message = "Заголовок поста не может быть пустым!")
    @Size(min = 5, max = 255, message = "Заголовок поста должен быть от 5 до 255 знаков")
    private String title;
    @NotBlank(message = "Content поста не может быть пустым ")
    private String content;
    private List<Long> tagIds = new ArrayList<>();
}
