package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
//import com.example.bookmanager.repository.dto.BookNameAndCategory;
import com.example.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Modifying
    @Query(value = "update book set category = 'none'",nativeQuery = true)
    // 팬텀리드 구현
    void update();

    List<Book> findByCategoryIsNull();
    List<Book> findAllByDeletedIsFalse();
    List<Book> findByCategoryIsNullAndDeletedIsFalse();
    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createAt,LocalDateTime updateAt);

    @Query(value = "select b from Book b where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null") //JPQL
    List<Book> findByNameRecently(@Param("name") String name, @Param("createdAt") LocalDateTime createdAt, @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name,b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.example.bookmanager.repository.dto.BookNameAndCategory(b.name,b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);
}