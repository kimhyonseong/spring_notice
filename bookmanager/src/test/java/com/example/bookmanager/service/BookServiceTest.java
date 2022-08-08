package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.repository.PublisherRepository;
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
    @Autowired
    private PublisherRepository publisherRepository;

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

    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA 캐스케이드");
        //bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");
        //publisherRepository.save(publisher);

        // save가 없어도 cascade로 인한 연관관계 삽입
        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("케스케이드");
        bookRepository.save(book1);

        System.out.println("publisher : "+publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
        //bookRepository.delete(book2);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);
        bookRepository.save(book3);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());
        System.out.println("book3 : "+bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void cascadeRemoveTest() {
        // data.sql은 auditable을 하지 않고 쿼리만 실행시킴
        bookRepository.deleteById(1L);

        System.out.println("books : "+bookRepository.findAll());
        System.out.println("publishers : "+publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }
}