package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Abdullah, Day 2 project is running!";
    }
}