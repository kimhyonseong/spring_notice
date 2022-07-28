package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void select() {
        List<Member> members = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        members.forEach(System.out::println);

        List<Member> members2 = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));
        members2.forEach(System.out::println);
        System.out.println();

        Member member = userRepository.getReferenceById(1L);  // get은 레이지 패치 트랜젝션 어노테이션 필요
        System.out.println(member);
        System.out.println();

        Member member1 = userRepository.findById(1L).orElse(null);
        System.out.println(member1);
        System.out.println();
    }

    @Test
    void save() {
        List<Member> memberPre = userRepository.findAll();
        memberPre.forEach(System.out::println);

        Member member1 = new Member("Jack","jack@naver.com");
        Member member2 = new Member("Jane","Jane@naver.com");

        userRepository.saveAll(Lists.newArrayList(member1,member2));

        List<Member> memberAfter = userRepository.findAll();
        memberAfter.forEach(System.out::println);
    }

    @Test
    void flush() {
        userRepository.saveAndFlush(new Member("hs","hs1@naver.com"));
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count() {
        long count = userRepository.count();
        System.out.println(count);
    }

    @Test
    void exist() {
        boolean exist = userRepository.existsById(1L);
        System.out.println(exist);
    }

    @Test
    void delete() {
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        List<Member> members = userRepository.findAll();
        members.forEach(System.out::println);

        userRepository.deleteAllInBatch();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void paging() {
        // 0부터 시작
        Page<Member> members = userRepository.findAll(PageRequest.of(0,3));

        System.out.println("page : "+members);
        System.out.println("totalElement : "+members.getTotalElements());
        System.out.println("totalPage : "+members.getTotalPages());
        System.out.println("numberOfElements : "+members.getNumberOfElements());
        System.out.println("size : "+members.getSize());

        members.getContent().forEach(System.out::println);
    }

    @Test
    void matcher() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email",endsWith());  //endsWith -> like 매치

        Example<Member> example = Example.of(new Member("kh","khs@naver.com"),matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }
}