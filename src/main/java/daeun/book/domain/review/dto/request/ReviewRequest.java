package daeun.book.domain.review.dto.request;

import daeun.book.domain.book.entity.Book;

public record ReviewRequest (
        Book book,
        String title,
        String content
) {
}
