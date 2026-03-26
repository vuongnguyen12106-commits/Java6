package org.example.demo.Service.IMP;

import lombok.RequiredArgsConstructor;
import org.example.demo.Controller.dto.BookRequest;
import org.example.demo.Controller.dto.BookResponse;
import org.example.demo.Entity.Book;
import org.example.demo.Repository.BookRepository;
import org.example.demo.Service.BookService;
import org.example.demo.exception.CustomResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements  BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map( book -> modelMapper.map(book, BookResponse.class)).toList();

    }

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest,  Book.class);
        book.setIsbn(generateIsbn());
        bookRepository.save(book);
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return bookResponse;
    }
    private String generateIsbn() {
        return "ISBN -"+ UUID.randomUUID().toString().substring(0, 13);
    }
    @Override
    public BookResponse updateBook(BookRequest bookRequest, long id) {
        return bookRepository.findById(id)
                .map(b ->{
                   if(bookRequest.getTitle() != null) b.setTitle(bookRequest.getTitle());
                   if(bookRequest.getAuthor() != null) b.setAuthor(bookRequest.getAuthor());
                   if(bookRequest.getPrice() != 0 ) b.setPrice(bookRequest.getPrice());
                   bookRepository.save(b);
                   return modelMapper.map(b, BookResponse.class);
                })
                .orElseThrow(()-> new CustomResourceNotFoundException("Book Not Found" +id));
    }

    @Override
    public void deleteBook(long id) {

    }

    @Override
    public BookResponse getBook(long id) {
        return bookRepository.findById(id).
                map(book -> modelMapper.map(book, BookResponse.class))
                .orElseThrow(() ->new CustomResourceNotFoundException("Book not found" +id));
    }
}
