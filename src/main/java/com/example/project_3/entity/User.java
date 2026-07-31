package com.example.project_3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Users")
@Setter
@Getter
public class User extends BaseEntity{
    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;


    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
}
