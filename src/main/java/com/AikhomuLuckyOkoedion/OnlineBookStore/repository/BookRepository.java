package com.AikhomuLuckyOkoedion.OnlineBookStore.repository;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByYear(int year);
    List<Book> findByGenre(String genre);
}

