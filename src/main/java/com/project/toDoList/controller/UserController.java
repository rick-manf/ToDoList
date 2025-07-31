package com.project.toDoList.controller;

import com.project.toDoList.dto.UserCreateWithPasswordDTO;
import com.project.toDoList.dto.UserDTO;
import com.project.toDoList.model.User;
import com.project.toDoList.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    // Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }




    @Operation (description = "Restituisce tutti gli utenti", responses = {
            @ApiResponse(description = "Operazione riuscita", responseCode = "200")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
}

    @Operation (description = "Restituisce un utente per ID", responses = {
            @ApiResponse(description = "Operazione riuscita", responseCode = "200")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation (description = "Crea un nuovo utente", responses = {
            @ApiResponse(description = "Operazione riuscita", responseCode = "201")
    })
    @PostMapping
    public ResponseEntity<UserCreateWithPasswordDTO> createUser(UserCreateWithPasswordDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @Operation (description = "Aggiorna un utente esistente", responses = {
            @ApiResponse(description = "Operazione riuscita", responseCode = "200")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @Operation (description = "Elimina un utente per ID", responses = {
            @ApiResponse(description = "Operazione riuscita", responseCode = "204")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
