package com.userservice.controller;

import com.userservice.dto.UserResponse;
import com.userservice.entity.UserEntity;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse>createUser(@RequestBody UserEntity userentity){
        log.info("Create user request received");
        UserResponse userResponse = userService.createUser(userentity);
        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(userResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.info("Get user request received ");
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Get all users request received");
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

}
