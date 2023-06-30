package com.example.jwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  @NotNull
  @Size(min = 3, max = 50)
  private String username;

  @JsonProperty
  @NotNull
  @Size(min = 3, max = 100)
  private String password;

  @NotNull
  @Size(min = 3, max = 50)
  private String nickname;
}
