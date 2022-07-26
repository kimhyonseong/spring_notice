package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("khs1@one.com");
        user.setName("khs");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User user1 = new User(null,"khs","khs@one.com",LocalDateTime.now(),LocalDateTime.now());
        System.out.println(user1);
    }
}