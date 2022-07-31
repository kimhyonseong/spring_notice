package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHistoryRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    void userHistoryUpdateTest() {
        Member member = new Member();
        member.setEmail("khs123@naver.com");
        member.setName("김현성1");

        userRepository.save(member);

        member.setName("김현성 수정");
        userRepository.save(member);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}