package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

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
}
