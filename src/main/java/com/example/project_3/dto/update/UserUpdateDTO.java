package com.example.project_3.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDTO {
    @Size(min = 3, max = 55, message = "Имя должно быть от 3  до 55 символов символов")
    private String name;
    @Email(message = "Email имеет неверный формат!")
    private String email;

}
