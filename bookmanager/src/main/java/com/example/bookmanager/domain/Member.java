package com.example.bookmanager.domain;

import com.example.bookmanager.domain.listener.Auditable;
import com.example.bookmanager.domain.listener.MyEntityListener;
import com.example.bookmanager.domain.listener.UserEntityListener;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
//@EntityListeners(value = {MyEntityListener.class,UserEntityListener.class})
@EntityListeners(value = {UserEntityListener.class})
public class Member extends BaseEntity {
    @Id  // entity의 프라이머리키
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가, strategy ->
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @PostPersist
    public void postPersist() {
        System.out.println(">>> postPersist");
    }

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
