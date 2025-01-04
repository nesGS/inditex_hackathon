package com.hackathon.inditex.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @CrossOrigin(origins = "*")
    @GetMapping
    public String healthCheck() {
        return "API is working";
    }
}