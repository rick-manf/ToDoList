package com.project.toDoList.service;

import com.project.toDoList.dto.TaskDTO;
import com.project.toDoList.exception.TaskNotFoundException;
import com.project.toDoList.mapper.TaskMapper;
import com.project.toDoList.entity.Task;
import com.project.toDoList.entity.User;
import com.project.toDoList.repository.TaskRepository;
import com.project.toDoList.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    //Constructor injection
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }






    //Restituisce tutte le task
    public List<TaskDTO> getTasks() {

        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }


    //Restituisce la task tramite id
    public TaskDTO getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return TaskMapper.toDTO(task);
    }

    //Restituisce le task di un User
    public List<TaskDTO> getTaskByUserId (Long userId) {

        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }


    //Crea Task
    public TaskDTO createTask(TaskDTO taskDTO) {

        Task task = TaskMapper.toEntity(taskDTO);
        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("Utente con " + taskDTO.getUserId() + "non trovato"));
        task.setUser(user);

        taskRepository.save(task);

        return TaskMapper.toDTO(task);
    }


    //Aggiorna una Task
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setCompleted(taskDTO.isCompleted());

        taskRepository.save(task);

        return TaskMapper.toDTO(task);
    }


    //Cancella Task
    public TaskDTO deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);

        return TaskMapper.toDTO(task);
    }

}
