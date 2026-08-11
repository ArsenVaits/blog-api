package com.example.project_3.service;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.entity.Post;
import com.example.project_3.entity.Tag;
import com.example.project_3.entity.User;
import com.example.project_3.exception.PostNotFoundException;
import com.example.project_3.mapper.PostMapper;
import com.example.project_3.repository.PostRepository;
import com.example.project_3.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final TagRepository tagRepository;


    //Create
    @Transactional
    public PostResponseDTO createPost(PostRequestDTO dto, User currentUser){
        Post post = postMapper.toEntity(dto);
        post.setUser(currentUser);
        if(!dto.tagIds().isEmpty()){
            List<Tag> tagsList = tagRepository.findAllById(dto.tagIds());
            post.setTagList(tagsList);
        }
        return postMapper.toResponseDTO(postRepository.save(post));
    }

    //update
    @Transactional
    public PostResponseDTO updatePostById(PostUpdateDTO dto, Long id, User currentUser){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));

        if(!post.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("Нет доступа!");
        }

        postMapper.update(dto, post);
        return postMapper.toResponseDTO(postRepository.save(post));
    }

    //Delete
    @Transactional
    public void deletePostById(Long id, User currentUser){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));
        if(!post.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("Нет доступа!");
        }
        postRepository.delete(post);

    }

    //Read
    public PostResponseDTO findPostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));

        return postMapper.toResponseDTO(post);
    }

    public Page<PostResponseDTO>  findPostsByTagId(Long tagId, Pageable pageable){
        return postRepository.findPostsByTagListId(tagId, pageable)
                .map(postMapper::toResponseDTO);
    }


    public Page<PostResponseDTO> findPostsByUserId(Long userId, Pageable pageable){
        return postRepository.findPostsByUserId(userId, pageable)
                .map(postMapper::toResponseDTO);
    }
}
