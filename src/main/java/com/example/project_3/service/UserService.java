package com.example.project_3.service;

import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.exception.UserNotFoundException;
import com.example.project_3.mapper.UserMapper;
import com.example.project_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponseDTO updateUser(UserUpdateDTO dto, User currentUser) {
        userMapper.update(dto, currentUser);
        return userMapper.toResponseDTO(userRepository.save(currentUser));
    }

    public void deleteCurrentUser(User currentUser) {
        userRepository.delete(currentUser);
    }


    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getPageUser(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponseDTO);
    }


    @Transactional(readOnly = true)
    public List<UserResponseDTO> getListUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }


    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));

        return userMapper.toResponseDTO(user);
    }


}
