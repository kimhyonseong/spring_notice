package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {
    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    // HttpEntity와 다른 엔티티는 같이 못쓰는듯
    // HttpEntity, Req<User> 같이쓰면 500에러
    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(//HttpEntity<String> entity,
                          @RequestBody Req<User> user,
                          @PathVariable int userId,
                          @PathVariable String userName,
                          @RequestHeader("x-authorization") String authorization,
                          @RequestHeader("custom-header") String customHeader) {
        //log.info("http entity : {}",entity.getBody());
        log.info("userId {}, userName : {}",userId,userName);
        log.info("authorization {}, customHeader : {}",authorization,customHeader);
        log.info("client req : {}",user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setHttpBody(user.getHttpBody());

        return response;
    }
}
