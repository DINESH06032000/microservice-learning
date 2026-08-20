package com.userservice.controller;

import com.userservice.dto.UserResponse;
import com.userservice.entity.UserEntity;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse>createUser(@RequestBody UserEntity userentity){
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(userService.createUser(userentity));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
