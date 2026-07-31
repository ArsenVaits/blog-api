package com.example.project_3.contoller;

import com.example.project_3.dto.request.PostRequestDTO;
import com.example.project_3.dto.response.PostResponseDTO;
import com.example.project_3.dto.update.PostUpdateDTO;
import com.example.project_3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostRequestDTO dto, @RequestParam Long userId){
        return postService.createPost(dto, userId);
    }

    @PatchMapping("/{id}")
    public PostResponseDTO updatePostById(@RequestBody PostUpdateDTO dto, @PathVariable Long id){
        return postService.updatePostById(dto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id){
        return postService.deletePostById(id);
    }

    @GetMapping("/{id}")
    public PostResponseDTO findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }


    @GetMapping("/tags/{tagId}")
    public Page<PostResponseDTO> findPostsByTagId(@PathVariable Long tagId, Pageable pageable){
        return postService.findPostsByTagId(tagId,pageable);
    }


    @GetMapping("/users/{userId}")
    public Page<PostResponseDTO> findPostsByUserId(@PathVariable Long userId, Pageable pageable){
        return postService.findPostsByUserId(userId, pageable);
    }

}
