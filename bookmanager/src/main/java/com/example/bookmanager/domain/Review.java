package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Book book;

    //@OneToMany(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.LAZY)  // 필요한 시점에 쿼리를 실행해 일반적으로 성능 좋음
    @JoinColumn(name = "review_id")
    private List<Comment> comments;
}
