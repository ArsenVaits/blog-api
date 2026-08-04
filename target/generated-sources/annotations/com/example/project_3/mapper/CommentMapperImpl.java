package com.example.project_3.mapper;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-05T01:49:19+0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponseDTO toResponseDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

        commentResponseDTO.setId( comment.getId() );
        commentResponseDTO.setContent( comment.getContent() );

        return commentResponseDTO;
    }

    @Override
    public Comment toEntity(CommentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setContent( dto.getContent() );

        return comment;
    }

    @Override
    public void update(CommentUpdateDTO dto, Comment comment) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getContent() != null ) {
            comment.setContent( dto.getContent() );
        }
    }
}
