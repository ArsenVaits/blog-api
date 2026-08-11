package com.example.project_3.security;

import com.example.project_3.dto.request.LoginRequestDTO;
import com.example.project_3.dto.request.RegisterRequestDTO;
import com.example.project_3.dto.response.AuthResponseDTO;
import com.example.project_3.entity.User;
import com.example.project_3.exception.UserAlreadyExistsException;
import com.example.project_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь уже существует!");
        }
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());

        userRepository.save(user);
        String token =  jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO dto){
        Authentication authenticate =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.username(),
                dto.password()
        ));

        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }

}

