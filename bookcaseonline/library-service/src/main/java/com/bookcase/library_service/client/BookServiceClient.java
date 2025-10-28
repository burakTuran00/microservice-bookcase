package com.bookcase.library_service.client;

import com.bookcase.library_service.dto.BookDto;
import com.bookcase.library_service.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", path = "/v1/book")
public interface BookServiceClient {
    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable("isbn") @NotEmpty String isbn);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception) {
        logger.info("Fallback method invoked for getBookById with isbn: {}", isbn);
        return ResponseEntity.ok(new BookIdDto(
                "default-book",
                "default-isbn"
        ));
    }

    @GetMapping("/{bookId}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getBookById(@PathVariable("bookId") @NotEmpty String bookId);

    default ResponseEntity<BookDto> getBookByIdFallback(String bookId, Exception exception) {
        logger.info("Book not found by id " + bookId + ", returning default BookDto object");
        return ResponseEntity.ok(new BookDto(
                new BookIdDto("default-book", "isbn"),
                "Default Title",
                2002,
                "Default Author",
                "Default Publisher"
        ));
    }
}
