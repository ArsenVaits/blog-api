package com.example.project_3.repository;

import com.example.project_3.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findTagsByPostListId(Long postId, Pageable pageable);
    boolean existsByName(String name);
}
