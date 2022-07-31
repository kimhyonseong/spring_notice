package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("위인전");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }
}