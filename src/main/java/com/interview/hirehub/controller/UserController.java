package com.interview.hirehub.controller;

import com.interview.hirehub.dto.request.CreateUserRequest;
import com.interview.hirehub.dto.request.LoginRequest;
import com.interview.hirehub.dto.response.ApiResult;
import com.interview.hirehub.dto.response.LoginResponse;
import com.interview.hirehub.entity.User;
import com.interview.hirehub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Public - Register
    @PostMapping("/register")
    public ApiResult<User> createUser(@Valid @RequestBody CreateUserRequest request) {

        User savedUser = userService.saveUser(request);

        return new ApiResult<>(
                true,
                "User created successfully",
                savedUser
        );
    }

    // Public - Login
    @PostMapping("/login")
    public ApiResult<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        return new ApiResult<>(
                true,
                "Login Successful",
                response
        );
    }

    // ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResult<List<User>> getAllUsers() {

        return new ApiResult<>(
                true,
                "Users fetched successfully",
                userService.getAllUsers()
        );
    }

    // Any Logged-in User
    @GetMapping("/{id}")
    public ApiResult<User> getUserById(@PathVariable Long id) {

        return new ApiResult<>(
                true,
                "User fetched successfully",
                userService.getUserById(id)
        );
    }

    // ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResult<String> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ApiResult<>(
                true,
                "User deleted successfully",
                "Deleted"
        );
    }
}