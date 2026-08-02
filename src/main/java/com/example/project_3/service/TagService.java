package com.example.project_3.service;

import com.example.project_3.dto.request.TagRequestDTO;
import com.example.project_3.dto.response.TagResponseDTO;
import com.example.project_3.dto.update.TagUpdateDTO;
import com.example.project_3.entity.Tag;
import com.example.project_3.exception.TagNotFoundException;
import com.example.project_3.mapper.TagMapper;
import com.example.project_3.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    //CREATE

    public TagResponseDTO createTag(TagRequestDTO dto){
        Tag tag = tagRepository.save(tagMapper.toEntity(dto));
        return tagMapper.toResponseDTO(tag);
    }


    //DELETE

    public ResponseEntity<Void> deleteTagById(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Тег с таким Id не был найден!"));

        tagRepository.delete(tag);
        return ResponseEntity.noContent().build();
    }

    //UPDATE


    public TagResponseDTO updateTagById(TagUpdateDTO dto, Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Тег с таким Id не был найден!"));
        tagMapper.update(dto, tag);
        return tagMapper.toResponseDTO(tagRepository.save(tag));
    }

    //READ
    @Transactional(readOnly = true)
    public TagResponseDTO findTagById(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Тег с таким Id не был найден!"));
        return tagMapper.toResponseDTO(tag);
    }


    @Transactional(readOnly = true)
    public Page<TagResponseDTO> getTagsByPostId(Long postId, Pageable pageable){

        return tagRepository.findTagsByPostListId(postId, pageable).map(tagMapper::toResponseDTO);
    }
}
