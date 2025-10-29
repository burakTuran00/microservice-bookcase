package com.bookcase.library_service.service;

import com.bookcase.library_service.client.BookServiceClient;
import com.bookcase.library_service.dto.AddBookRequest;
import com.bookcase.library_service.dto.LibraryDto;
import com.bookcase.library_service.exception.LibraryNotFoundException;
import com.bookcase.library_service.model.Library;
import com.bookcase.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LibraryService implements ILibraryService{

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    @Override
    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));

        LibraryDto libraryDto = new LibraryDto(
                library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookById)
                        .map(ResponseEntity::getBody)
                        .toList()
        );

        return libraryDto;
    }

    @Override
    public LibraryDto createLibrary() {
        Library savedLibrary = libraryRepository.save(new Library());
        return new LibraryDto(savedLibrary.getId());
    }

    @Override
    public void addBookToLibrary(AddBookRequest request) {
        String bookId = bookServiceClient.getBookByIsbn(request.getIsbn()).getBody().getBookId();
        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + request.getId()));

        library.getUserBook().add(bookId);
        libraryRepository.save(library);
    }

    @Override
    public List<String> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(Library::getId)
                .toList();
    }
}
