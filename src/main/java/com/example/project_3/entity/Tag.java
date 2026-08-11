package com.example.project_3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Tag")
@Setter
@Getter
public class Tag extends BaseEntity {
    private String name;


    @ManyToMany(mappedBy = "tagList")
    private List<Post> postList;
}
