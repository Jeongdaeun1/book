package daeun.book.domain.review.controller;

import daeun.book.domain.review.dto.ReviewDto;
import daeun.book.domain.review.dto.request.ReviewRequest;
import daeun.book.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> reviewRegister(@RequestBody ReviewRequest request){
        reviewService.reviewRegister(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviewList(){
        List<ReviewDto> reviewlist = reviewService.getReviewList();
        return ResponseEntity.ok(reviewlist);
    }
}
