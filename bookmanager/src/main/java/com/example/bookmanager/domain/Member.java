package com.example.bookmanager.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
//@EntityListeners(value = {MyEntityListener.class,UserEntityListener.class})
@EntityListeners(value = {AuditingEntityListener.class,UserEntityListener.class})
public class Member implements Auditable{
    @Id  // entity의 프라이머리키
    @GeneratedValue  // 자동 증가
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist() {
//        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }

    @PostPersist
    public void postPersist() {
        System.out.println(">>> postPersist");
    }

//    @PreUpdate
//    public void preUpdate() {
//        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }

    @PostUpdate
    public void postUpdate() {
        System.out.println(">>> PostUpdate");
    }

    @PreRemove
    public void PreRemove() {
        System.out.println(">>> PreRemove");
    }

    @PostRemove
    public void PostRemove() {
        System.out.println(">>> PostRemove");
    }

    @PostLoad
    public void PostLoad() {
        System.out.println(">>> PostLoad");
    }
}
