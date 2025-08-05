package daeun.book.domain.book.controller;

import daeun.book.domain.book.dto.BookDto;
import daeun.book.domain.book.dto.request.BookRequest;
import daeun.book.domain.book.entity.Book;
import daeun.book.domain.book.repository.BookRepository;
import daeun.book.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //TODO:책목록조회
    @GetMapping
    public ResponseEntity<List<BookDto>> getBookList() {
        List<BookDto> bookList = bookService.getBookList();

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookDetail(@PathVariable Long bookId) {
        BookDto bookDetail = bookService.getBookDetail(bookId);

        return ResponseEntity.ok(bookDetail);
    }
}
