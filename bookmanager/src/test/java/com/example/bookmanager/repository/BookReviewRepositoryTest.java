package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewRepositoryTest {
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void reviewInsertTest() {
        Book book = givenBook();
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(book);
        bookReviewInfo.setAvgScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewRepository.save(bookReviewInfo);

        System.out.println(">>> "+bookReviewRepository.findAll());
    }

    @Test
    void relationTest() {
        givenBookReview();

        // relation - book을 bookReview id를 이용해서 찾음
        Book result = bookReviewRepository.findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();

        System.out.println(result);

        // relation - bookReview를 book id를 이용하여 찾음
        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();
        System.out.println(result2);
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("위인전");
        book.setAuthorId(1L);
        book.setCategory("사회");
        //book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReview() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAvgScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewRepository.save(bookReviewInfo);

        System.out.println(bookReviewRepository.findAll());
    }
}