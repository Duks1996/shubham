package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.payload.ApiResponse;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    public ApiResponse createReview(Review review, AppUser appUser, Long propertyid){
        Optional<Property> byId = propertyRepository.findById(propertyid);
        if (byId.isEmpty()) return new ApiResponse(false, null, "Property does not exists with id " + propertyid);

        Property property = byId.get();
        Review existingReview = reviewRepository.findByAppUserAndProperty(appUser, property);
        if (existingReview!=null) return new ApiResponse(false, null, "Review already exists for this property and user");

        review.setAppUser(appUser);
        review.setProperty(property);
        return new ApiResponse(true,reviewRepository.save(review),"Review created successfully");
    }

    public List<Review> listReviewOfUser(AppUser appUser){
        return reviewRepository.findByAppUser(appUser);
    }

    public int deleteReview(AppUser appUser, Long propertyid) {
        Optional<Property> byId = propertyRepository.findById(propertyid);
        if (byId.isEmpty()) return 3;
        Property property = byId.get();

        Review existingReview = reviewRepository.findByAppUserAndProperty(appUser, property);

        if (existingReview==null) {
            return 1;
        }else {
            reviewRepository.deleteById(existingReview.getId());
            return 2;
        }
    }
}
