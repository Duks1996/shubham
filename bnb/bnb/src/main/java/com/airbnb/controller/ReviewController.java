package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Review re = reviewService.createReview(review,appUser,id);
        return re!=null? new ResponseEntity<>(re, HttpStatus.OK):new ResponseEntity<>("Reviews not exists",HttpStatus.OK);
    }
}
