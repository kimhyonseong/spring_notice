package com.example.bookmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;  // 시

    private String district;  // 구

    @Column(name = "address_detail")
    private String detail;

    private String zipCode;
}
