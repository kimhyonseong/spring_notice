package com.example.jwt.service;

import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.Authority;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public User signup(UserDto userDto) {
    if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername())
            .orElse(null) != null) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다.");
    }

    Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

    User user = User.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .nickname(userDto.getNickname())
            .authorities(Collections.singleton(authority))
            .activated(true)
            .build();

    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public Optional<User> getUserWithAuthorities(String username) {
    return userRepository.findOneWithAuthoritiesByUsername(username);
  }

  @Transactional
  public Optional<User> getMyUserWithAuthorities() {
    return SecurityUtil.getCurrentUser()
            .flatMap(userRepository::findOneWithAuthoritiesByUsername);
  }
}
