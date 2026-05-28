package com.librarymanagement.controller;

import com.librarymanagement.entity.Book;
import com.librarymanagement.service.BookService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean addBook(String id, String title, String author, String genre, int copies) {
        return bookService.addBook(new Book(id, title, author, genre, copies));
    }

    public Optional<Book> getBookById(String id) {
        return bookService.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public boolean updateBook(String id, String title, String author, String genre, int copies) {
        return bookService.updateBook(id, title, author, genre, copies);
    }

    public boolean deleteBook(String id) {
        return bookService.deleteBook(id);
    }
}
