package com.bookcase.library_service.service;

import com.bookcase.library_service.dto.AddBookRequest;
import com.bookcase.library_service.dto.LibraryDto;
import com.bookcase.library_service.model.Library;

public interface ILibraryService {
    LibraryDto getAllBooksInLibraryById(String libraryId);
    LibraryDto createLibrary();
    void  addBookToLibrary(AddBookRequest request);
}
