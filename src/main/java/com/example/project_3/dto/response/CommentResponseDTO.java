package com.example.project_3.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Long postId;
    private Long userId;

}
