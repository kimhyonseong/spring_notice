package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);  // 인자는 사용할 컬럼

    Member findMemberByName(String name);  // 단일 결과를 불러와야 성공
    List<Member> findMemberByEmail(String email);
    Member findByEmail(String email);

    Member findTop1ByEmail(String email);  // 맨 위부터 하나만
    List<Member> findTop2ByEmail(String email);  // 맨 위부터 두개

    List<Member> findMemberByNameAndEmail(String name, String email);
    List<Member> findMemberByNameOrEmail(String name, String email);
    List<Member> findByCreatedAtAfter(LocalDateTime yesterday);  // 크기 비교
    List<Member> findByIdAfter(Long id);  // 숫자도 가능하지만 가독성을 위해 날짜에 사용
    List<Member> findByIdGreaterThan(Long id);  // 숫자 크기비교
    List<Member> findByIdGreaterThanEqualAndNameLike(Long id,String name);  // 숫자 크기비교

    List<Member> findFirstByName(String name, Sort sort);

    Page<Member> findByName(String name, Pageable pageable);
}
