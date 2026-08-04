package com.example.project_3.mapper;

import com.example.project_3.dto.request.TagRequestDTO;
import com.example.project_3.dto.response.TagResponseDTO;
import com.example.project_3.dto.update.TagUpdateDTO;
import com.example.project_3.entity.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-05T01:49:19+0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagResponseDTO toResponseDTO(Tag t) {
        if ( t == null ) {
            return null;
        }

        TagResponseDTO tagResponseDTO = new TagResponseDTO();

        tagResponseDTO.setId( t.getId() );

        return tagResponseDTO;
    }

    @Override
    public Tag toEntity(TagRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Tag tag = new Tag();

        return tag;
    }

    @Override
    public void update(TagUpdateDTO dto, Tag tag) {
        if ( dto == null ) {
            return;
        }
    }
}
