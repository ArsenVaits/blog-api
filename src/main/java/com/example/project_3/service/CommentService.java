package com.example.project_3.service;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.entity.Comment;
import com.example.project_3.entity.Post;
import com.example.project_3.entity.User;
import com.example.project_3.exception.CommentNotFoundException;
import com.example.project_3.exception.PostNotFoundException;
import com.example.project_3.mapper.CommentMapper;
import com.example.project_3.repository.CommentRepository;
import com.example.project_3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.access.AccessDeniedException;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;



    //Create
    public CommentResponseDTO createComment(CommentRequestDTO dto, User currentUser, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));


        Comment comment = commentMapper.toEntity(dto);
        comment.setUser(currentUser);
        comment.setPost(post);

        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }


    //Delete
    public void deleteCommentById(Long id, User currentUser){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));
        if(!comment.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("Нет прав!");
        }
        commentRepository.delete(comment);
    }


    //Update
    public CommentResponseDTO updateCommentById(CommentUpdateDTO dto, Long id, User currentUser){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));
        if(!comment.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("Нет прав!");
        }
        commentMapper.update(dto,comment);
        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }

    //Read
    @Transactional(readOnly = true)
    public CommentResponseDTO findCommentById(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));
        return commentMapper.toResponseDTO(comment);
    }

    @Transactional(readOnly = true)
    public Page<CommentResponseDTO> findCommentsByPostId(Long postId, Pageable pageable){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден!"));
        return commentRepository.findCommentsByPostId(postId, pageable)
                .map(commentMapper::toResponseDTO);
    }




}
