package com.example.project_3.mapper;

import com.example.project_3.dto.request.UserRequestDTO;
import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-01T06:10:58+0500",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponseDTO(User u) {
        if ( u == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( u.getId() );
        userResponseDTO.setName( u.getName() );
        userResponseDTO.setEmail( u.getEmail() );

        return userResponseDTO;
    }

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public void update(UserUpdateDTO dto, User u) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            u.setName( dto.getName() );
        }
        if ( dto.getEmail() != null ) {
            u.setEmail( dto.getEmail() );
        }
    }
}
