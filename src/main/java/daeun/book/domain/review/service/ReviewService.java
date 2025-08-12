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

    @Transactional
    public ReviewDto getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new IllegalArgumentException("없는 게시물 입니다."));
        return new ReviewDto(review);
    }

    @Transactional
    public void modReview(Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        // 엔티티가 제공하는 update 메서드로 값 변경
        review.update(request.title(), request.content());
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        // 1. 삭제할 리뷰가 존재하는지 확인 (없으면 예외 발생)
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다.");
        }

        // 2. ID를 이용해 바로 리뷰 삭제
        reviewRepository.deleteById(reviewId);
    }
}
