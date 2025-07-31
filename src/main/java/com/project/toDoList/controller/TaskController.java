package com.project.toDoList.controller;

import com.project.toDoList.dto.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.toDoList.service.TaskService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    //Constructor injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }




    @Operation (description = "Restituisce tutte le task", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "200", content = @Content (mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks () {

        return ResponseEntity.ok(taskService.getTasks());
    }

    @Operation (description = "Restituisce tutte le task di un utente", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "200", content = @Content (mediaType = "application/json"))
    })
    @GetMapping({"/user/{userId}"})
    public ResponseEntity<List<TaskDTO>> getAllUserTasks (@PathVariable Long userId){

        List<TaskDTO> tasks = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(tasks);
    }


    @Operation (description = "Restituisce una task per ID", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "200", content = @Content (mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation (description = "Crea una nuova task", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "201", content = @Content (mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.createTask(taskDTO));
    }

    @Operation (description = "Aggiorna una task esistente", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "200", content = @Content (mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
            return ResponseEntity.ok(taskService.updateTask(id, taskDTO));

    }

    @Operation (description = "Elimina una task per ID", responses = {
        @ApiResponse (description = "Successful Operation", responseCode = "200", content = @Content (mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

}
