package com.userservice.service;

import com.userservice.dto.UserResponse;
import com.userservice.entity.UserEntity;
import com.userservice.exception.EmailAlreadyExistsException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userrepository;

    @Transactional
    public UserResponse createUser(UserEntity userEntity) {

        if (userrepository.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists ");
        }

        UserEntity savedUser = userrepository.save(userEntity);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public UserResponse getUserById(Long id){
        return toResponse(findOrThrow(id));
    }

    public List<UserResponse> getAllUsers(){
        return userrepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse toResponse(UserEntity e){
        return UserResponse.builder().id(e.getId()).name(e.getName()).email(e.getEmail()).build();
    }

    private UserEntity findOrThrow(Long id) {
        return userrepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found: " + id));
    }
}
