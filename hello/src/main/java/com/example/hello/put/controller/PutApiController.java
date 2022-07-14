package com.example.hello.put.controller;

import com.example.hello.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/put")
public class PutApiController {
    @PutMapping("/put")
    public PostRequestDto put(@RequestBody PostRequestDto postDto) {
        System.out.println(postDto);
        return postDto;
    }

    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto postDto, @PathVariable(value = "userId") Long id) {
        System.out.println(id);
        return postDto;
    }
}
