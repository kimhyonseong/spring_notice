package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        bookRepository.findAllByDeletedIsFalse().forEach(System.out::println);
//        bookRepository.findByCategoryIsNullAndDeletedIsFalse().forEach(System.out::println);
    }

    @Test
    void queryTest() {
        bookRepository.findAll().forEach(System.out::println);

        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : "+
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                        "JPA 패키지", LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)
                ));

        System.out.println("findByNameRecently : "+bookRepository.findByNameRecently("JPA 패키지",LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)));

        bookRepository.findBookNameAndCategory().forEach(b -> {
            System.out.println(b.getName() + " : " + b.getCategory());
        });

        bookRepository.findBookNameAndCategory(PageRequest.of(1,1)).forEach(
                bookCate -> { System.out.println(bookCate.getName()+" : "+bookCate.getCategory());});

        bookRepository.findBookNameAndCategory(PageRequest.of(0,1)).forEach(
                bookCate -> { System.out.println(bookCate.getName()+" : "+bookCate.getCategory());});
    }

    @Test
    void nativeQueryTest() {
        /*
        bookRepository.findAll().forEach(System.out::println);
        bookRepository.findAllCustom().forEach(System.out::println);
         */

        List<Book> bookList = bookRepository.findAll();

        for (Book book : bookList) {
            book.setCategory("IT 전문서적");
        }

        bookRepository.saveAll(bookList);
        bookRepository.findAll().forEach(System.out::println);

        System.out.println("updated row : "+bookRepository.updateCategories());
        bookRepository.findAll().forEach(System.out::println);

        bookRepository.showTables().forEach(System.out::println);
    }

    @Test
    void converterTest() {
        bookRepository.findAll().forEach(System.out::println);
    }
}