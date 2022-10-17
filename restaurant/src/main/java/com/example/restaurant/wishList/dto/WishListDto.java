package com.example.restaurant.wishList.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 데이터베이스 변수명 변화가 있으면 영향을 미쳐서 따로 분리
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {
    private Integer index;
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
