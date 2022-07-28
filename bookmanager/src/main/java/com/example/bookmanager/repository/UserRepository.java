package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<Member,Long> {
}
