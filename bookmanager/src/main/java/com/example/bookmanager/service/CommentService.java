package com.example.bookmanager.service;

import com.example.bookmanager.domain.Comment;
import com.example.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for(int i = 0; i<10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고에요");

            //commentRepository.save(comment);
        }
    }

    //@Transactional  // 이거로 인해 영속성 컨텍스트가 관리 -> 엔티티 변경 내용이 있으면 save -> 더티체크 대상이됨
    @Transactional(readOnly = true)  // 더티체크 피하기
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for(Comment comment : comments) {
            comment.setComment("별로에요");

            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
        // 영속화 되어있지 않기 때문에 save 전에는 더티체크가 일어나지 않음
        Comment comment = new Comment();
        comment.setComment("이건 뭘까요");

        commentRepository.save(comment);
    }
}
