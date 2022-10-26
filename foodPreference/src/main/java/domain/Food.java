package domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food extends Date{
  private int foodId;
  private String name;
  private String isUse;
  private String image;
  private Integer category;
  private String worker;
}
