package com.bookcase.book_service.service;

import com.bookcase.book_service.dto.BookDto;
import com.bookcase.book_service.dto.BookIdDto;
import com.bookcase.book_service.model.Book;

import java.util.List;

public interface IBookService {
    List<BookDto> getAllBooks();
    BookIdDto findByIsbn(String isbn);
    BookDto findBookDetailsById(String id);
}
