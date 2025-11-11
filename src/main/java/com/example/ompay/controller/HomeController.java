package com.example.ompay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/welcome")
    public String home() {
        return "Bienvenue dans ton API Spring Boot 🚀";
    }
}
