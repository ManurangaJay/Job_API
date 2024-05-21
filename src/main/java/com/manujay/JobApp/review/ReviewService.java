package com.manujay.JobApp.review;

import java.util.List;

public interface ReviewService {
    List<Review>  getAllReviews(Long companyId);
    Boolean addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);
}
