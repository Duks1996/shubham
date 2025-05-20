package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.ApiResponse;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/createreview")
    public ResponseEntity<?> createReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam Long id
            ){
        ApiResponse response = reviewService.createReview(review, appUser, id);
        if(response.getSuccess()){
            return new ResponseEntity<>(response.getReview(),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(response.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userreviews")
    public ResponseEntity<List<Review>> listReviewOfUser(@AuthenticationPrincipal AppUser appUser){
        return new ResponseEntity<>(reviewService.listReviewOfUser(appUser),HttpStatus.OK);
    }
}
