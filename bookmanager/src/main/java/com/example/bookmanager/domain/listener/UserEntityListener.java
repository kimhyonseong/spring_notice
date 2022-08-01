package com.example.bookmanager.domain.listener;

import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        Member member = (Member) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(member.getId());
        userHistory.setName(member.getName());
        userHistory.setEmail(member.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
