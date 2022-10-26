package domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
abstract public class Date {
  private LocalDateTime regDate;
  private LocalDateTime updDate;
}
