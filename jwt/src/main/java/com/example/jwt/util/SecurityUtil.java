package com.example.jwt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
  private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

  private SecurityUtil() {}

  public static Optional<String> getCurrentUser() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      log.debug("Security Context에 인증정보가 없습니다.");
      return Optional.empty();
    }

    String username = "";
    if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
      username = springSecurityUser.getUsername();
    } else if(authentication.getPrincipal() instanceof String) {
      username = (String) authentication.getPrincipal();
    }

    return Optional.ofNullable(username);
  }
}
