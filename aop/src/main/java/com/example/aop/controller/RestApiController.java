package com.example.aop.controller;

import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
//        System.out.println("get");
//        System.out.println("id = " + id);
//        System.out.println("name = " + name);
        System.out.println();
        return id+" "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        System.out.println();
//        System.out.println("post");
//        System.out.println(user);
        return user;
    }
}
