package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-----------");

        var objectMapper = new ObjectMapper();

        //object -> text
        // object mapper get method 사용
        // private 변수에 대한 get 메소드 외 get 사용시 에러 발생
        var user = new User("khs",10,"010-000-0001");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        //text -> object
        // object mapper는 디폴트 생성자 필요
        var objectUser = objectMapper.readValue(text,User.class);
        System.out.println(objectUser);

    }

}
