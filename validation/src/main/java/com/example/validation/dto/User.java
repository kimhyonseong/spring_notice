package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;

import javax.validation.constraints.*;
import java.util.List;

public class User {
    @NotBlank
    private String name;
    @Max(value = 90)
    private int age;

    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
//AssertTrue 어노테이션을 사용하는 메소드는 is로 시작해야함
//    @AssertTrue(message = "yyyyMM 형식에 맞지 않습니다.")
//    public boolean isReqYearMonthValidation() {
//
//
//        return true;
//    }
}
