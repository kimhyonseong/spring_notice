package com.example.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @JsonIgnore
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @Column(name = "username", length = 50, unique = true)
  private String username;

  @JsonIgnore
  @Column(name = "password",length = 100)
  private String password;

  @Column(name = "nickname", length = 50)
  private String nickname;

  @JsonIgnore
  @Column(name = "activated")
  private boolean activated;

  @ManyToMany
  @JoinTable(
          name = "user_authority",
          joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
          inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "authority_name")
  )
  private Set<Authority> authorities;
}
