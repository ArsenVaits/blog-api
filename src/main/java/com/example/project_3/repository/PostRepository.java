package com.example.project_3.repository;

import com.example.project_3.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findPostsByTagListId(Long tagId, Pageable pageable);

    Page<Post> findPostsByUserId(Long userId, Pageable pageable);
}
