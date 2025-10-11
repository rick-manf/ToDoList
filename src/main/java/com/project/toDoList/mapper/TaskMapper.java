package com.project.toDoList.mapper;

import com.project.toDoList.dto.TaskDTO;
import com.project.toDoList.entity.Task;

public class TaskMapper {

    public static Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) return null;
        return Task.builder()
                .id(taskDTO.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .dueDate(taskDTO.getDueDate())
                .completed(taskDTO.isCompleted())
                .user(null) // User should be set separately, as it requires a User entity
                .build();
    }

    public static TaskDTO toDTO(Task task) {
        if (task == null) return null;
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .completed(task.isCompleted())
                .userId(task.getUser() != null ? task.getUser().getId() : null) // Optional, if you want to include user information
                .build();
    }
}
