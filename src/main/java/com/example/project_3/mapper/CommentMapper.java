package com.example.project_3.mapper;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.entity.Comment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponseDTO toResponseDTO(Comment comment);

    Comment toEntity(CommentRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(CommentUpdateDTO dto, @MappingTarget Comment comment);
}
