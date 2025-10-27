package com.bookcase.book_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String title;
    private int bookYear;
    private String author;
    private String pressName;
    private String isbn;

    //Overloaded constructor without id for easier instantiation
    public Book(String title, int bookYear, String author, String pressName, String isbn) {
        this.isbn = isbn;
        this.pressName = pressName;
        this.author = author;
        this.bookYear = bookYear;
        this.title = title;
    }
}
