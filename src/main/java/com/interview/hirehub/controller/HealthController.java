package com.interview.hirehub.controller;

import com.interview.hirehub.dto.response.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResult<String> health() {

        return new ApiResult<>(
                true,
                "HireHub Backend is Running Successfully",
                "Welcome to HireHub 🚀"
        );
    }
}