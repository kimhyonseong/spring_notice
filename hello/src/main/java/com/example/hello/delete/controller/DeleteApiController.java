package com.example.hello.delete.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delete")
public class DeleteApiController {
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account) {
        System.out.println(userId);
        System.out.println(account);

        // 리소스 삭제 200
    }
}
