package daeun.book.domain.review.dto;

import daeun.book.domain.book.entity.Book;
import daeun.book.domain.review.entity.Review;

public record ReviewDto(
        Long reviewId,
        Book book,
        String title,
        String content
) {

    public ReviewDto(Review review){
        this(
                review.getId(),
                review.getBook(),
                review.getTitle(),
                review.getContent()
        );
    }
}
