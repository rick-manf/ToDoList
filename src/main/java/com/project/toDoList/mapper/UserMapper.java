package com.project.toDoList.mapper;

import com.project.toDoList.dto.UserDTO;
import com.project.toDoList.entity.User;

public class UserMapper {
    public static UserDTO toDto(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
    }}