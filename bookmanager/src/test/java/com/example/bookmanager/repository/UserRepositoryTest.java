package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

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

    @Test
    void customSelect() {
        System.out.println("findByName : "+userRepository.findByName("khs"));
        System.out.println("findMemberByName : " + userRepository.findMemberByName("khs1"));
        System.out.println("findMemberByEmail : "+userRepository.findMemberByEmail("khs@naver.com"));
        System.out.println("Top1ByEmail : "+userRepository.findTop1ByEmail("khs@naver.com"));
        System.out.println("Top2ByEmail : "+userRepository.findTop2ByEmail("khs@naver.com"));
    }

    @Test
    void whereSelect() {
        System.out.println("findMemberByNameAndEmail : "+userRepository.findMemberByNameAndEmail("khs","khs@naver.com"));
        System.out.println("findMemberByNameOrEmail : "+userRepository.findMemberByNameOrEmail("khs","khs@naver.com"));
        System.out.println("findByCreatedAtAfter : "+userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : "+userRepository.findByIdAfter(4L));
        System.out.println("findByIdGreaterThan : "+userRepository.findByIdGreaterThan(4L));
        System.out.println("findByIdGreaterThanEqualAndNameLike : "+userRepository.findByIdGreaterThanEqualAndNameLike(4L,"%khs%"));
    }

    @Test
    void sortAndPagingTest() {
        System.out.println("findFirstByName asc : "+userRepository.findFirstByName("khs",Sort.by(Sort.Order.asc("id"))));
        System.out.println("findFirstByName desc : "+userRepository.findFirstByName("khs",Sort.by(Sort.Order.desc("id"))));

        System.out.println("findByName paging : "+userRepository.findByName("khs",PageRequest.of(0,2,Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void listenerTest() {
        Member member = new Member("khs10","khs10@naver.com");
        userRepository.save(member);

        Member member1 = userRepository.findMemberByName("khs10");
        member1.setEmail("hs10@naver.com");
        userRepository.save(member1);

        userRepository.deleteById(1L);
    }

    @Test
    void persistTest() {
        Member member = new Member("khs10","khs10@naver.com");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUpdatedAt(LocalDateTime.now());
        userRepository.save(member);
        System.out.println(userRepository.findMemberByEmail("khs10@naver.com"));
    }

    @Test
    void preUpdateTest() {
        Member member = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("now : "+member);

        member.setEmail("empty@naver.com");
        userRepository.save(member);

        System.out.println("updated : "+userRepository.findAll().get(0));
    }

    @Test
    void userRelationTest() {
        Member member = new Member();
        member.setName("David");
        member.setEmail("david@naver.com");
        userRepository.save(member);
        userHistoryRepository.findAll().forEach(System.out::println);

        member.setName("David1");
        userRepository.save(member);
        userHistoryRepository.findAll().forEach(System.out::println);

        member.setEmail("david1@naver.com");
        userRepository.save(member);
        userHistoryRepository.findAll().forEach(System.out::println);
    }
}