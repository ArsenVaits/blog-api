package com.example.project_3.service;

import com.example.project_3.dto.request.CommentRequestDTO;
import com.example.project_3.dto.response.CommentResponseDTO;
import com.example.project_3.dto.update.CommentUpdateDTO;
import com.example.project_3.entity.Comment;
import com.example.project_3.entity.Post;
import com.example.project_3.entity.User;
import com.example.project_3.exception.CommentNotFoundException;
import com.example.project_3.exception.PostNotFoundException;
import com.example.project_3.exception.UserNotFoundException;
import com.example.project_3.mapper.CommentMapper;
import com.example.project_3.repository.CommentRepository;
import com.example.project_3.repository.PostRepository;
import com.example.project_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;



    //Create
    public CommentResponseDTO createComment(CommentRequestDTO dto, Long userId, Long postId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не был найден!"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден"));


        Comment comment = commentMapper.toEntity(dto);
        comment.setUser(user);
        comment.setPost(post);

        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }


    //Delete
    public ResponseEntity<Void> deleteCommentById(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));
        commentRepository.delete(comment);
        return ResponseEntity.noContent().build();
    }


    //Update
    public CommentResponseDTO updateCommentById(CommentUpdateDTO dto, Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));

        commentMapper.update(dto,comment);
        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }

    //Read
    public CommentResponseDTO findCommentById(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с таким id не найден!"));
        return commentMapper.toResponseDTO(comment);
    }

    public Page<CommentResponseDTO> findCommentsByPostId(Long postId, Pageable pageable){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Пост с таким id не был найден!"));
        return commentRepository.findCommentsByPostId(postId, pageable)
                .map(commentMapper::toResponseDTO);
    }




}
