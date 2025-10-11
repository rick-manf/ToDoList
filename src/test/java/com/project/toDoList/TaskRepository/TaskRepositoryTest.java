package com.project.toDoList.TaskRepository;

import com.project.toDoList.entity.Task;
import com.project.toDoList.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




// This class will contain tests for the TaskRepository
// It will include methods to test CRUD operations, pagination, and filtering of tasks
// The tests will use JUnit and Mockito for mocking dependencies

public class TaskRepositoryTest {



//     Example
//     @Test
//     public void testFindAllTasks() {
//
//         // Arrange
//         TaskRepository taskRepository = mock(TaskRepository.class);
//         List<Task> tasks = Arrays.asList(new Task(1L, "Task 1", "Description 1", LocalDate.now(), false));
//         when(taskRepository.findAll()).thenReturn(tasks);
//
//         // Act
//         List<Task> result = taskRepository.findAll();
//
//         // Assert
//         assertEquals(1, result.size());
//         assertEquals("Task 1", result.get(0).getTitle());
//     }




    @Test
    void TaskRepository_SaveAll_ReturnSavedTask() {

        //Arrange
        TaskRepository taskRepository = mock(TaskRepository.class);
        Task task = Task.builder()
                .title("Task1")
                .description("Description1")
                .dueDate(LocalDate.now())
                .completed(false)
                .build();
        when(taskRepository.save(task)).thenReturn(task);


        //Act
        Task savedTask = taskRepository.save(task);

        //Assert
        Assertions.assertNotNull(task);
        Assertions.assertEquals("Task1", savedTask.getTitle());
        Assertions.assertEquals("Description1", savedTask.getDescription());
        Assertions.assertEquals(LocalDate.now(), savedTask.getDueDate());
        Assertions.assertFalse(savedTask.isCompleted());
    }
}

