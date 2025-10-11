package com.project.toDoList.mapper;

import com.project.toDoList.dto.UserCreateWithPasswordDTO;
import com.project.toDoList.entity.User;

public class UserWithPasswordMapper {
    // This class is used to map the DTO with password
    public static UserCreateWithPasswordDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserCreateWithPasswordDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(UserCreateWithPasswordDTO dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
