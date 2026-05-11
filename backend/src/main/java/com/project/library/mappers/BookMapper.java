package com.project.library.mappers;

import org.springframework.stereotype.Component;

import com.project.library.dto.BookDTO;
import com.project.library.entities.Book;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedDate());
    }

    public Book toEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.publishedDate());
    }
}