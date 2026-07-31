package com.example.project_3.mapper;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.entity.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-01T03:45:19+0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostResponseDTO toResponseDTO(Post p) {
        if ( p == null ) {
            return null;
        }

        PostResponseDTO postResponseDTO = new PostResponseDTO();

        postResponseDTO.setId( p.getId() );
        postResponseDTO.setTitle( p.getTitle() );
        postResponseDTO.setContent( p.getContent() );

        return postResponseDTO;
    }

    @Override
    public Post toEntity(PostRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( dto.getTitle() );
        post.setContent( dto.getContent() );

        return post;
    }

    @Override
    public void update(PostUpdateDTO dto, Post p) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTitle() != null ) {
            p.setTitle( dto.getTitle() );
        }
        if ( dto.getContent() != null ) {
            p.setContent( dto.getContent() );
        }
    }
}
