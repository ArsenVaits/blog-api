package com.example.project_3.mapper;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.entity.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponseDTO toResponseDTO(Post p);
    Post toEntity(PostRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(PostUpdateDTO dto, @MappingTarget Post p);
}
