package com.example.project_3.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;


public record PostRequestDTO (
    @NotBlank(message = "Заголовок поста не может быть пустым!")
    @Size(min = 5, max = 255, message = "Заголовок поста должен быть от 5 до 255 знаков")
    String title,
    @NotBlank(message = "Content поста не может быть пустым ")
    String content,
    List<Long> tagIds
){
    public PostRequestDTO{
        if(tagIds == null){
            tagIds = List.of();
        }
    }
}
