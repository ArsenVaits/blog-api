package com.example.project_3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    @Column(name = "username", nullable = false, length = 30, unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", nullable = false, length = 60)
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Post> postList;


    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}
