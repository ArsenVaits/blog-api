package com.example.project_3.mapper;

import com.example.project_3.dto.request.UserRequestDTO;
import com.example.project_3.dto.response.UserResponseDTO;
import com.example.project_3.dto.update.UserUpdateDTO;
import com.example.project_3.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User u);
    User toEntity(UserRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UserUpdateDTO dto, @MappingTarget User u);
}
