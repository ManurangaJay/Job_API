package com.manujay.JobApp.review.impl;

import com.manujay.JobApp.company.Company;
import com.manujay.JobApp.company.CompanyService;
import com.manujay.JobApp.review.Review;
import com.manujay.JobApp.review.ReviewRepository;
import com.manujay.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId()
                .equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(reviewRepository.existsById(reviewId)){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (getReviewById(companyId, reviewId) != null){
//            Review review = getReviewById(companyId, reviewId);
//            Company company = review.getCompany();
//            company.getReviews().remove(review);
//            review.setCompany(null);
//            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else {
            return false;
        }
    }
}