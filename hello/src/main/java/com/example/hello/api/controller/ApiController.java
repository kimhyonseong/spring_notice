package com.example.hello.api.controller;

import com.example.hello.api.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //rest api 처리하는 controller
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/text")
    public String text(@RequestParam String account) {
        return account;
    }

    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user;
    }

    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
