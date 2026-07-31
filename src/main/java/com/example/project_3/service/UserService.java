package com.example.project_3.service;

import com.example.project_3.dto.request.UserRequestDTO;
import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.exception.UserNotFoundException;
import com.example.project_3.mapper.UserMapper;
import com.example.project_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toResponseDTO(user);
    }


    public UserResponseDTO updateUser(UserUpdateDTO dto, Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));

        userMapper.update(dto, user);
        return userMapper.toResponseDTO(userRepository.save(user));
    }


    public Page<UserResponseDTO> getPageUser(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(userMapper::toResponseDTO);
    }



    public List<UserResponseDTO> getListUser(){
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }


    public ResponseEntity<Void> deleteUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    public UserResponseDTO findUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));

        return userMapper.toResponseDTO(user);
    }


}
