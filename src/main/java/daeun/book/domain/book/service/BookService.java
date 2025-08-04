package daeun.book.domain.book.service;

import daeun.book.domain.book.dto.request.BookRequest;
import daeun.book.domain.book.entity.Book;
import daeun.book.domain.book.enums.ReadStatus;
import daeun.book.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public void bookRegister(BookRequest request) {
        String name = request.name();
        String author = request.author();
        String publisher = request.publisher();
        ReadStatus status = request.status();

        Book book = Book.builder()
                .name(name)
                .author(author)
                .publisher(publisher)
                .status(status)
                .build();

        bookRepository.save(book);
    }
}
