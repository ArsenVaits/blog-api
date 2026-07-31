package com.example.project_3.mapper;

import com.example.project_3.dto.request.TagRequestDTO;
import com.example.project_3.dto.response.TagResponseDTO;
import com.example.project_3.dto.update.TagUpdateDTO;
import com.example.project_3.entity.Tag;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponseDTO toResponseDTO(Tag t);
    Tag toEntity(TagRequestDTO dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(TagUpdateDTO dto, @MappingTarget Tag tag);
}
