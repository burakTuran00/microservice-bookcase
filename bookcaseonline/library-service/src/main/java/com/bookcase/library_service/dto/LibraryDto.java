package com.bookcase.library_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {

    private String id;

    @Builder.Default
    private List<BookDto> userBookList = new ArrayList<>();

    public LibraryDto(String id) {
        this.id = id;
    }
}
