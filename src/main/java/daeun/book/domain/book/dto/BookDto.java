package daeun.book.domain.book.dto;

import daeun.book.domain.book.entity.Book;
import daeun.book.domain.book.enums.ReadStatus;
import daeun.book.domain.book.repository.BookRepository;

public record BookDto (

            Long bookId,
            String name,
            String author,
            String publisher,
            ReadStatus status
){
    //Book 엔티티를 BookDto로 변환하는 생성자 추가
    public BookDto(Book book) {
        this(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getPublisher(),
                book.getStatus()
        );
    }
}
