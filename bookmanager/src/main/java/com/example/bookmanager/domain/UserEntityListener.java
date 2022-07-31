package com.example.bookmanager.domain;

import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {
    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        Member member = (Member) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setId(member.getId());
        userHistory.setName(member.getName());
        userHistory.setEmail(member.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
