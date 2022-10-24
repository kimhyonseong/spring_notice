package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

    //https://openapi.naver.com/v1/search/local.json
    // ?query=%EA%B0%88%EB%B9%84%EC%B0%9C
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("/naver")
    public String naver() {
//        String query = "갈비찜";
//        String encodeStr = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("v1/search/local.json")
                .queryParam("query","갈비찜")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        //헤더를 사용하기위해해
       RequestEntity<Void> req = RequestEntity
               .get(uri)
               .header("X-Naver-Client-Id","gVXIGSRGaIUJI1osCJl5")
               .header("X-Naver-Client-Secret","dnnazd_DWt")
               .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        log.info("result : {}",result.getBody());
        log.info("uri : {}",uri);

        return result.getBody();
    }
}
