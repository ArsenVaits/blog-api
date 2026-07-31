package com.example.project_3.dto.update;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PostUpdateDTO {
    @Size(min = 5, max = 255, message = "Заголовок поста должен быть от 5 до 255 знаков!")
    private String title;
    private String content;
    private List<Long> tagIds = new ArrayList<>();
}
