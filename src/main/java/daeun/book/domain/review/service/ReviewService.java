package daeun.book.domain.review.service;


import daeun.book.domain.book.entity.Book;
import daeun.book.domain.review.dto.ReviewDto;
import daeun.book.domain.review.dto.request.ReviewRequest;
import daeun.book.domain.review.entity.Review;
import daeun.book.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public void reviewRegister(ReviewRequest request){
        Book book = request.book();
        String title = request.title();
        String content = request.content();

        Review review = Review.builder()
                .book(book)
                .title(title)
                .content(content)
                .build();
        reviewRepository.save(review);
    }

    @Transactional
    public List<ReviewDto> getReviewList() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }
}
