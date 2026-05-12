package com.project.library.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.library.dto.BookDTO;
import com.project.library.entities.Book;
import com.project.library.exceptions.EntityNotFoundException;
import com.project.library.mappers.BookMapper;
import com.project.library.repositories.BookRepository;

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

    @Transactional(readOnly = true)
    public BookDTO getBookById(UUID id) {
        BookDTO result = bookMapper.toDTO(getBookEntityById(id));
        return result;
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> pagedBooks = bookRepository.findAll(pageable);

        return pagedBooks.map(bookMapper::toDTO);
    }

    @Transactional
    public BookDTO updateBook(UUID id, BookDTO bookDto) {
        Book existingBook = getBookEntityById(id);

        existingBook.setTitle(bookDto.title());
        existingBook.setAuthor(bookDto.author());
        existingBook.setPublishedDate(bookDto.publishedDate());

        Book savedBook = bookRepository.save(existingBook);

        return bookMapper.toDTO(savedBook);
    }

    @Transactional
    public void deleteBook(UUID id) {
        Book book = getBookEntityById(id);

        bookRepository.delete(book);
    }

    private Book getBookEntityById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found!"));
    }
}
