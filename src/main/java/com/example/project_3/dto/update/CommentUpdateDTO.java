package com.example.project_3.dto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentUpdateDTO {
    @NotBlank(message = "Комментарий не может быть пустым!")
    private String content;
}
