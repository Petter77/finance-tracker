package com.petter77.financetracker.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.petter77.financetracker.service.UserService;
import com.petter77.financetracker.dto.response.UserResponse;
import com.petter77.financetracker.dto.request.CreateUserRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService; 
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
