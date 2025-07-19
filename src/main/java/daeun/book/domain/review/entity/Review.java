package daeun.book.domain.review.entity;

import daeun.book.domain.book.entity.Book;
import daeun.book.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="book_reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String title;

    //java string = varchar(255) -> TEXT
    @Column(columnDefinition = "TEXT")
    private String content;
}
