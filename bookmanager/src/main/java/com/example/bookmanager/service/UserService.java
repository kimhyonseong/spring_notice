package com.example.bookmanager.service;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        // 비영속 상태
        Member member = new Member();
        member.setName("newUser");
        member.setEmail("newUser@naver.com");

        // 영속
        entityManager.persist(member);
        entityManager.detach(member);

        member.setEmail("newUserAfter@naver.com");
        entityManager.merge(member);
        entityManager.flush();

        entityManager.clear();

        Member member1 = userRepository.findById(1L).get();
        entityManager.remove(member1);

//        member1.setName("After");
//        entityManager.merge(member1);
        // 이미 지워진건 머지가 안됨
    }
}
