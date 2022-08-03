package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발1");
        Book book4 = givenBook("개발2");

        Author author1 = givenAuthor("khs");
        Author author2 = givenAuthor("khs2");

        book1.addAuthor(author1);
        book2.addAuthor(author2);
        book3.addAuthor(author1,author2);
        book4.addAuthor(author1,author2);
        //book2.setAuthors(Lists.newArrayList(author2));
        //book3.setAuthors(Lists.newArrayList(author1,author2));

        //author1.setBooks(Lists.newArrayList(book1,book3,book4));
        //author2.setBooks(Lists.newArrayList(book2,book3,book4));
        author1.addBook(book1,book3,book4);
        author2.addBook(book2,book3,book4);

        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        authorRepository.saveAll(Lists.newArrayList(author1,author2));

        System.out.println("authors through book : "+bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author : "+bookRepository.findAll().get(2).getAuthors());
    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }
}