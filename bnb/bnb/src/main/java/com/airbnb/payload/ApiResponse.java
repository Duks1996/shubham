package com.airbnb.payload;

import com.airbnb.entity.Review;
import lombok.Getter;

@Getter
public class ApiResponse {
    private Review review;
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, Review review, String message) {
        this.review = review;
        this.success = success;
        this.message = message;
    }
}
