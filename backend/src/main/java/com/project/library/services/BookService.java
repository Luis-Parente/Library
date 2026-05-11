package com.project.library.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.library.dto.BookDTO;
import com.project.library.entities.Book;
import com.project.library.mappers.BookMapper;
import com.project.library.repositories.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookDTO createBook(BookDTO bookDto) {
        Book bookEntity = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(bookEntity);

        return bookMapper.toDTO(savedBook);
    }

    @Transactional
    public BookDTO getBookById(UUID id) {
        BookDTO result = bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return result;
    }

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> pagedBooks = bookRepository.findAll(pageable);

        return pagedBooks.map(bookMapper::toDTO);
    }

    @Transactional
    public BookDTO updateBook(UUID id, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(bookDto.title());
        existingBook.setAuthor(bookDto.author());
        existingBook.setPublishedDate(bookDto.publishedDate());

        Book savedBook = bookRepository.save(existingBook);

        return bookMapper.toDTO(savedBook);
    }

    @Transactional
    public String deleteBook(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);

        return "Book deleted successfully";
    }
}
