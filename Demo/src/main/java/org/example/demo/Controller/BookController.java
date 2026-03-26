package org.example.demo.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demo.Controller.dto.BookRequest;
import org.example.demo.Controller.dto.BookResponse;
import org.example.demo.Entity.Book;
import org.example.demo.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(id));
    }
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
       return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookRequest));

    }
    @PutMapping("{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookRequest bookRequest,@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookRequest,id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<BookResponse> deleteBook(@Valid @RequestBody BookRequest bookRequest, @PathVariable long id) {
       bookService.deleteBook(id);
       return ResponseEntity.noContent().build();

    }
}
