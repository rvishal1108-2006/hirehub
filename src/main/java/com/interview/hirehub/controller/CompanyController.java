package com.interview.hirehub.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/company/dashboard")
    public String companyDashboard() {
        return "Welcome Company";
    }
}