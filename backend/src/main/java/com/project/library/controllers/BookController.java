package com.project.library.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.library.services.BookService;

import jakarta.validation.Valid;

import com.project.library.dto.RequestBookDTO;
import com.project.library.dto.ResponseBookDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseBookDTO> registerBook(@RequestBody @Valid RequestBookDTO newBook) {
        ResponseBookDTO result = bookService.createBook(newBook);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{uuid}").buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable UUID id) {
        ResponseBookDTO result = bookService.getBookById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ResponseBookDTO>> getAllBooks(Pageable pageable) {
        Page<ResponseBookDTO> result = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ResponseBookDTO> updateBook(@PathVariable UUID id, @RequestBody @Valid RequestBookDTO bookDto) {
        ResponseBookDTO result = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
