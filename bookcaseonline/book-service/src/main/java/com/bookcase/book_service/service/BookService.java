package com.bookcase.book_service.service;

import com.bookcase.book_service.dto.BookDto;
import com.bookcase.book_service.dto.BookIdDto;
import com.bookcase.book_service.exception.BookNotFoundException;
import com.bookcase.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::convert)
                .collect(Collectors.toList());
    }

    @Override
    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .map(book -> new BookIdDto(
                        book.getId(),
                        book.getIsbn()
                ))
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
    }

    @Override
    public BookDto findBookDetailsById(String id) {
        return bookRepository.findById(id)
                .map(BookDto::convert)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

}
