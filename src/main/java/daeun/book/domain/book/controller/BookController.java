package daeun.book.domain.book.controller;

import daeun.book.domain.book.dto.request.BookRequest;
import daeun.book.domain.book.repository.BookRepository;
import daeun.book.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Void> bookRegister(@RequestBody BookRequest request) {
        bookService.bookRegister(request);
        return ResponseEntity.ok().build();
    }
}
