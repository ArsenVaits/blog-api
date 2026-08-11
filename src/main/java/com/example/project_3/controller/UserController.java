package com.example.project_3.controller;

import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;


    @PatchMapping
    public UserResponseDTO updateUser(@Valid @RequestBody UserUpdateDTO dto, @AuthenticationPrincipal User currentUser){
        return userService.updateUser(dto, currentUser);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(@AuthenticationPrincipal User currentUser){
        userService.deleteCurrentUser(currentUser);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/list")
    public List<UserResponseDTO> getListUsers(){
        return userService.getListUser();
    }

    @GetMapping("/page")
    public Page<UserResponseDTO> getPageUsers(Pageable pageable){
        return userService.getPageUser(pageable);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }






}
