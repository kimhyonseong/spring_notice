package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("위인전");
        book.setAuthorId(1L);
        //book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        Member member = userRepository.findByEmail("khs@naver.com");

        System.out.println(member.getUserHistories());
        System.out.println(member);

        System.out.println("Review : "+member.getReviews());
        System.out.println("Book : "+member.getReviews().get(0).getBook());
        System.out.println("Publisher : "+member.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("NoName");

        return publisherRepository.save(publisher);
    }

    private Member givenUser() {
        return userRepository.findByEmail("khs@naver.com");
    }

    private void givenReview(Member member, Book book) {
        Review review = new Review();
        review.setTitle("내인생...");
        review.setContent("허망한 내인생");
        review.setScore(4.0f);
        review.setMember(member);
        review.setBook(book);

//        System.out.println("givenReview : "+member);
//        System.out.println("givenBook : "+book);

        reviewRepository.save(review);

        reviewRepository.findAll().forEach(System.out::println);
    }
}