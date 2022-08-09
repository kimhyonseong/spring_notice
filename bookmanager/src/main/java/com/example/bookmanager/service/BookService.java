package com.example.bookmanager.service;

import com.example.bookmanager.domain.Author;
import com.example.bookmanager.domain.Book;
import com.example.bookmanager.repository.AuthorRepository;
import com.example.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    //@Transactional(rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() throws Exception{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
            System.out.println();
        }

//        Author author = new Author();
//        author.setName("good");
//
//        authorRepository.save(author);

//        throw new RuntimeException("오류 발생");
//        throw new Exception("오류 발생");
    }

    //@Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id) {
        System.out.println(">>> "+bookRepository.findById(id));
        System.out.println(">>> "+bookRepository.findAll());
        entityManager.clear();

        System.out.println(">>> "+bookRepository.findById(id));
        System.out.println(">>> "+bookRepository.findAll());
        bookRepository.update();
        entityManager.clear();

//        Book book = bookRepository.findById(id).get();
//        book.setName("change");
//        bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }
}
