package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // @GetMapping("/")
    // public String hello() {
    //     return "Hello World from Spring Boot!";
    // }

    public static int addNumbers(int a, int b) {
        return a + b;
    }
}
