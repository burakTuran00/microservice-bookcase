package com.bookcase.book_service.dto;

import com.bookcase.book_service.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private BookIdDto id;
    private String title;
    private int bookYear;
    private String author;
    private String pressName;

    public static BookDto convert(Book from) {
        return BookDto.builder()
                .id(from.getId() != null ? BookIdDto.convert(from.getId(), from.getIsbn()) : null)
                .title(from.getTitle())
                .bookYear(from.getBookYear())
                .author(from.getAuthor())
                .pressName(from.getPressName())
                .build();
    }
}
