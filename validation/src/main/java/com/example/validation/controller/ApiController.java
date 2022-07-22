package com.example.validation.controller;

import com.example.validation.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) {
        // valid에 대한 에러를 바인딩함
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();

            bindingResult.getAllErrors().forEach(objectError -> {
                // 어떤 필드에서 에러가 났는지 가져옴
                FieldError field = (FieldError) objectError;
                String msg = objectError.getDefaultMessage();

                System.out.println("field = " + field);
                System.out.println("msg = " + msg);

                sb.append("field = ").append(field.getField());
                sb.append("msg = ").append(msg);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);

        return ResponseEntity.ok(user);
    }
}
