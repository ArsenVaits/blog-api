package com.example.project_3.service;

import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import com.example.project_3.exception.UserNotFoundException;
import com.example.project_3.mapper.UserMapper;
import com.example.project_3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserResponseDTO userResponse;

    @BeforeEach
    void setUp() {
        user = createUser("arsen", "arsen@example.com");
        userResponse = new UserResponseDTO(1L, "arsen", "arsen@example.com");
    }

    @Test
    void updateUserUpdatesSavesAndReturnsUser() {
        UserUpdateDTO updateDTO = new UserUpdateDTO("new-arsen", "new@example.com");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponseDTO(user)).thenReturn(userResponse);

        UserResponseDTO result = userService.updateUser(updateDTO, user);

        assertThat(result).isEqualTo(userResponse);
        verify(userMapper).update(updateDTO, user);
        verify(userRepository).save(user);
        verify(userMapper).toResponseDTO(user);
    }

    @Test
    void deleteCurrentUserDeletesUser() {
        userService.deleteCurrentUser(user);

        verify(userRepository).delete(user);
    }

    @Test
    void getPageUserReturnsMappedPage() {
        User secondUser = createUser("anna", "anna@example.com");
        UserResponseDTO secondResponse = new UserResponseDTO(2L, "anna", "anna@example.com");
        Pageable pageable = PageRequest.of(0, 2);
        Page<User> users = new PageImpl<>(List.of(user, secondUser), pageable, 2);

        when(userRepository.findAll(pageable)).thenReturn(users);
        when(userMapper.toResponseDTO(user)).thenReturn(userResponse);
        when(userMapper.toResponseDTO(secondUser)).thenReturn(secondResponse);

        Page<UserResponseDTO> result = userService.getPageUser(pageable);

        assertThat(result.getContent()).containsExactly(userResponse, secondResponse);
        assertThat(result.getTotalElements()).isEqualTo(2);
        verify(userRepository).findAll(pageable);
    }

    @Test
    void getListUserReturnsMappedUsers() {
        User secondUser = createUser("anna", "anna@example.com");
        UserResponseDTO secondResponse = new UserResponseDTO(2L, "anna", "anna@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user, secondUser));
        when(userMapper.toResponseDTO(user)).thenReturn(userResponse);
        when(userMapper.toResponseDTO(secondUser)).thenReturn(secondResponse);

        List<UserResponseDTO> result = userService.getListUser();

        assertThat(result).containsExactly(userResponse, secondResponse);
        verify(userRepository).findAll();
    }

    @Test
    void findUserByIdReturnsUserWhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toResponseDTO(user)).thenReturn(userResponse);

        UserResponseDTO result = userService.findUserById(1L);

        assertThat(result).isEqualTo(userResponse);
        verify(userRepository).findById(1L);
        verify(userMapper).toResponseDTO(user);
    }

    @Test
    void findUserByIdThrowsExceptionWhenUserDoesNotExist() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findUserById(99L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Пользователь с таким id не найден!");

        verify(userRepository).findById(99L);
        verify(userMapper, never()).toResponseDTO(user);
    }

    private User createUser(String username, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        return newUser;
    }
}
