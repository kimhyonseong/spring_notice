package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try {
            bookService.putBookAndAuthor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("book : "+bookRepository.findAll());
        System.out.println("author : "+authorRepository.findAll());
    }

    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> test : "+bookRepository.findAll());
    }

    @Test
    void propagationTest() {
        try {
            bookService.putBookAndAuthor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("book : "+bookRepository.findAll());
        System.out.println("author : "+authorRepository.findAll());
    }
}