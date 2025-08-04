package daeun.book.domain.book.entity;


import daeun.book.domain.book.enums.ReadStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private ReadStatus status;

    private String image;
}

//snake_case = book_service book_id (db)
//camelCase = (bookService) bookId (java 변수 이름)
//PascalCase = (BookService) BookId (java 클래스 이름)
