package com.example.bookmanager.service;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select m from Member m").getResultList());
    }

    /*
    영속성 캐시가 DB에 반영되는 조건
    1. flush 메소드를 명시적으로 호출하는 시점
    2. 트랜잭션이 끝나서 커밋되는 시점
    3. 복잡한 조건의 jpql 쿼리가 실행되는 시점
     */

    @Test
    void findCacheTest() {
//        System.out.println(userRepository.findByEmail("khs@naver.com"));
//        System.out.println(userRepository.findByEmail("khs@naver.com"));
//        System.out.println(userRepository.findByEmail("khs@naver.com"));

        // Jpa 1차 캐시 - id를 통해서
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
    }

    @Test
    void cacheFlushTest() {
        Member member = userRepository.findById(1L).get();
        member.setName("이순신");
        userRepository.save(member);
        System.out.println("-------------------------------------------");
        member.setEmail("Lee@naver.com");
        userRepository.save(member);

        //DB에는 존재하지 않고 영속성 context에만 존재함
        System.out.println(">>> 1 : "+userRepository.findById(1L).get());
        // 영속성 캐시를 데이터베이스에 동기화
        userRepository.flush();
        //DB에도 존재함
        System.out.println(">>> 2 : "+userRepository.findById(1L).get());
    }

    @Test
    void complexQueryTest() {
        Member member = userRepository.findById(1L).get();
        member.setName("이순신");
        userRepository.save(member);
        System.out.println("-------------------------------------------");
        member.setEmail("Lee@naver.com");
        userRepository.save(member);

        System.out.println(userRepository.findById(1L).get());
    }
}
