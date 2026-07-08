package com.interview.hirehub.controller;

import com.interview.hirehub.dto.response.ApiResult;
import com.interview.hirehub.entity.User;
import com.interview.hirehub.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResult<User> createUser(@RequestBody User user) {

        User savedUser = userService.saveUser(user);

        return new ApiResult<>(
                true,
                "User created successfully",
                savedUser
        );
    }

    @GetMapping
    public ApiResult<List<User>> getAllUsers() {

        return new ApiResult<>(
                true,
                "Users fetched successfully",
                userService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    public ApiResult<User> getUserById(@PathVariable Long id) {

        return new ApiResult<>(
                true,
                "User fetched successfully",
                userService.getUserById(id)
        );
    }

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