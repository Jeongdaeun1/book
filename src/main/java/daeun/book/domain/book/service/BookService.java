package daeun.book.domain.book.service;

import daeun.book.domain.book.dto.BookDto;
import daeun.book.domain.book.dto.request.BookRequest;
import daeun.book.domain.book.entity.Book;
import daeun.book.domain.book.enums.ReadStatus;
import daeun.book.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<BookDto> getBookList() {
        List<Book> books = bookRepository.findAll();
        //DB에서 모든 BOOk 엔티티 가져온다

        //Book 엔티티 리스트를 BookDto 리스트로 변환
        return books.stream()
                .map(BookDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public BookDto getBookDetail(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new IllegalArgumentException("해당 ID의 책을 찾을 수 없습니다. id=" + bookId));


        return new BookDto(book);

    }
}
