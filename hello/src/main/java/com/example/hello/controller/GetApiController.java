package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    // localhost:9090/api/get/hello
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // localhost:9090/api/get/hi
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }

    // localhost:9090/api/get/path-value/{name}
    @GetMapping("/path-value/{name}")
    //public String pathValue(@PathVariable String name) {
    public String pathValue(@PathVariable(name = "name") String paramName) {
        System.out.println(paramName);
        return paramName;
    }

    // localhost:9090/api/get/query-param?user=khs&email=khs6524@naver.com
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();

        queryParam.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
            System.out.println();

            sb.append(key).append(" = ").append(value).append("\n");
        });

        return sb.toString();
    }

    @GetMapping("query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam Integer age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

    // 객체가 인자로 들어오면 해당 객체와 쿼리파라미터와 맵핑함
    @GetMapping("query-param3")
    public String queryParam3(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
