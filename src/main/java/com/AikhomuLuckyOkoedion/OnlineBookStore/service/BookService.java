package com.AikhomuLuckyOkoedion.OnlineBookStore.service;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Book;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> searchBooks(String title, String author, String genre, Integer year) {
        if (title != null) return bookRepository.findByTitleContainingIgnoreCase(title);
        if (author != null) return bookRepository.findByAuthorContainingIgnoreCase(author);
        if (genre != null) return bookRepository.findByGenre(genre);
        if (year != null) return bookRepository.findByYear(year);
        return List.of();
    }

    @Transactional
    public Book addBook(Book book) {

        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }


    }
}

