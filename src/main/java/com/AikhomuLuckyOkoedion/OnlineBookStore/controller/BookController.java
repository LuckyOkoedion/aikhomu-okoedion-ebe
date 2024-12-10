package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Book;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public List<Book> searchBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) Integer year) {
        return bookService.searchBooks(title, author, genre, year);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
