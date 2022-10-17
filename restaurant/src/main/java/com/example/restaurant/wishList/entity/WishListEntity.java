package com.example.restaurant.wishList.entity;

import com.example.restaurant.db.MemoryDbEntity;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListEntity extends MemoryDbEntity {
    private String title;
    private String category;
    private String address;
    private String roadAddress;  //도로명주소
    private String homepageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;
}
