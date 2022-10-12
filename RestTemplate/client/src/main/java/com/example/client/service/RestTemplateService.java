package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.dto.UserResquest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public void post(){
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
        //ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);
        ResponseEntity<String> response = restTemplate.postForEntity(uri,req,String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        //return response.getBody();
    }

    public UserResponse exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();

        System.out.println(uri);

        UserResquest req = new UserResquest();
        req.setAge(10);
        req.setName("steve");

        RequestEntity<UserResquest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity,UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();

        System.out.println(uri);

        UserResquest userResquest = new UserResquest();
        userResquest.setAge(10);
        userResquest.setName("steve");

        Req<UserResquest> req = new Req<>();
        req.setHeader(
                new Req.Header()
        );
        req.setHttpBody(userResquest);

        RequestEntity<Req<UserResquest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header", "fffff")
                .body(req);

        // 제너릭 타입엔 .class 사용불가
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<UserResponse>> responseEntity =
                restTemplate.exchange(requestEntity,
                        new ParameterizedTypeReference<Req<UserResponse>>() {});

        return responseEntity.getBody();
    }
}