package com.interview.hirehub.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "Welcome Student";
    }
}