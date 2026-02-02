package com.petter77.financetracker.service;
import com.petter77.financetracker.model.User;
import com.petter77.financetracker.repository.UserRepository;
import com.petter77.financetracker.dto.request.CreateUserRequest;
import com.petter77.financetracker.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        } 

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        } 

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        
        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getCreatedAt(),
            savedUser.getUpdatedAt()
        );
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            UserResponse response = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
            );
            responses.add(response);
        }
        return responses;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
