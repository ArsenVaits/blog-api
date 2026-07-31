package com.example.project_3.dto.response;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Long userId;

}
