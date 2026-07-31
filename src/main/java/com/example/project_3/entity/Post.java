package com.example.project_3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Post")
@Setter
@Getter
public class Post extends BaseEntity{
    private String title;
    private String content;


    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
    @JoinTable(
            name = "Post_Tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
}
