package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    public Review createReview(Review review, AppUser appUser, Long id){
        Optional<Property> byId = propertyRepository.findById(id);
        if (byId.isPresent()){
            review.setAppUser(appUser);
            review.setProperty(byId.get());
            return reviewRepository.save(review);
        }else {
            return null;
        }
    }
}
