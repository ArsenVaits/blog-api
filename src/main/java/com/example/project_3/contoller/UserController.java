package com.example.project_3.contoller;

import com.example.project_3.dto.request.UserRequestDTO;
import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto){
        return userService.createUser(dto);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO updateUser(@RequestBody UserUpdateDTO dto, @PathVariable Long id){
        return userService.updateUser(dto, id);
    }

    @GetMapping("/list")
    public List<UserResponseDTO> getListUsers(){
        return userService.getListUser();
    }

    @GetMapping("/page")
    public Page<UserResponseDTO> getPageUsers(Pageable pageable){
        return userService.getPageUser(pageable);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }






}
