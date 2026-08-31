package com.userservice.service;

import com.userservice.dto.UserResponse;
import com.userservice.entity.UserEntity;
import com.userservice.exception.EmailAlreadyExistsException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userrepository;
    private static final Logger log =
            LoggerFactory.getLogger(UserService.class);

    @Transactional
    public UserResponse createUser(UserEntity userEntity) {
        log.info("Creating new user");
        if (userrepository.existsByEmail(userEntity.getEmail())) {
            log.warn("User creation failed: email already exists");
            throw new EmailAlreadyExistsException("Email already exists ");
        }
        UserEntity savedUser = userrepository.save(userEntity);
        log.info("User saved successfully");
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        log.info("User creation completed successfully ");
        return response;
    }

    public UserResponse getUserById(Long id) {
        log.info("Fetching user ");
        UserEntity user = findOrThrow(id);
        log.info("User retrieved successfully ");
        return toResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userrepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse toResponse(UserEntity e){
        return UserResponse.builder().id(e.getId()).name(e.getName()).email(e.getEmail()).build();
    }

    private UserEntity findOrThrow(Long id) {
        log.debug("Searching database ");
        return userrepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found ");
                    return new UserNotFoundException("User not found: " + id);
                });
    }
}
