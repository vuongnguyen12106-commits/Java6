package org.example.demo.Service;

import org.example.demo.Controller.dto.BookRequest;
import org.example.demo.Controller.dto.BookResponse;
import org.example.demo.Entity.Book;

import java.util.List;

public interface BookService {
    List<BookResponse> getAll();
    BookResponse addBook(BookRequest bookRequest);
    BookResponse updateBook(BookRequest bookRequest, long id);
    void  deleteBook(long id);
    BookResponse getBook(long id);
}
