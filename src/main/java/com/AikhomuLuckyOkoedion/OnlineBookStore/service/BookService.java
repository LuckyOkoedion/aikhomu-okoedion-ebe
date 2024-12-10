package com.AikhomuLuckyOkoedion.OnlineBookStore.service;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Book;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String title, String author, String genre, Integer year) {
        if (title != null) return bookRepository.findByTitleContainingIgnoreCase(title);
        if (author != null) return bookRepository.findByAuthorContainingIgnoreCase(author);
        if (genre != null) return bookRepository.findByGenre(genre);
        if (year != null) return bookRepository.findByYear(year);
        return List.of();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}

