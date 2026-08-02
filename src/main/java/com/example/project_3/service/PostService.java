package com.example.project_3.service;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.entity.Post;
import com.example.project_3.entity.Tag;
import com.example.project_3.entity.User;
import com.example.project_3.exception.PostNotFoundException;
import com.example.project_3.exception.UserNotFoundException;
import com.example.project_3.mapper.PostMapper;
import com.example.project_3.repository.PostRepository;
import com.example.project_3.repository.TagRepository;
import com.example.project_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    //Create
    public PostResponseDTO createPost(PostRequestDTO dto, Long userId){
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));
        Post post = postMapper.toEntity(dto);
        post.setUser(user);

        if(!dto.getTagIds().isEmpty()){
            List<Tag> tags = tagRepository.findAllById(dto.getTagIds());
            post.setTagList(tags);
        }

        return postMapper.toResponseDTO(postRepository.save(post));
    }

    //update
    public PostResponseDTO updatePostById(PostUpdateDTO dto, Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));
        postMapper.update(dto, post);
        return postMapper.toResponseDTO(postRepository.save(post));
    }

    //Delete
    public ResponseEntity<Void> deletePostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

    //Read
    @Transactional(readOnly = true)
    public PostResponseDTO findPostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));

        return postMapper.toResponseDTO(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDTO>  findPostsByTagId(Long tagId, Pageable pageable){
        return postRepository.findPostsByTagListId(tagId, pageable)
                .map(postMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDTO> findPostsByUserId(Long userId, Pageable pageable){
        return postRepository.findPostsByUserId(userId, pageable)
                .map(postMapper::toResponseDTO);
    }
}
