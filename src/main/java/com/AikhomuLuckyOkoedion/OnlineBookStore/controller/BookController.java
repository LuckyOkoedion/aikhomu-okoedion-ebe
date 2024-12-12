package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Book;
import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.BookDto;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.BookService;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.CustomMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CustomMetricService customMetricService;

    @GetMapping("/search")
    public List<Book> searchBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) Integer year) {
        customMetricService.incrementSearchCounter();
        return bookService.searchBooks(title, author, genre, year);
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto book) {
        Book theBook = new Book();
        theBook.setAuthor(book.getAuthor());
        theBook.setGenre(book.getGenre());
        theBook.setIsbn(book.getIsbn());
        theBook.setTitle(book.getTitle());
        theBook.setYear(book.getYear());
        return bookService.addBook(theBook);
    }
}

