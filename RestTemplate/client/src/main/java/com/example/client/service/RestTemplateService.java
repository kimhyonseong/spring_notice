package com.example.client.service;

import com.example.client.dto.UserResponse;
import com.example.client.dto.UserResquest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    // 요청 http://localhost/api/server/hello
    // response 받아오기

    public UserResponse hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","lss")
                .queryParam("age",1545)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse post(){
        // post 실제로 이렇게 안씀
        // http://localost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve") // {} 속에 차례대로 맵핑되는것을 보여주기 위함
                .toUri();

        System.out.println(uri);

        // post라 http body 필요 -> object -> object mapper -> json 변환 -> rest template가 http body에 json 넣어줌
        UserResquest req = new UserResquest();
        req.setAge(10);
        req.setName("steve");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        // 어떤 값이 잘못된지 모를땐 String으로 모든 값 확인 가능
        //ResponseEntity<String> response = restTemplate.postForEntity(uri,req,String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}