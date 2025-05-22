package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/createbooking")
    public ResponseEntity<?> createBooking(
            @RequestParam long propertyId,
            @RequestBody Booking booking,
            @AuthenticationPrincipal AppUser appUser
            ){
        Booking responsebooking = bookingService.createBooking(propertyId, booking, appUser);
        return responsebooking!=null ? new ResponseEntity<>(responsebooking, HttpStatus.CREATED) : new ResponseEntity<>("Failed to book", HttpStatus.BAD_REQUEST);
    }
}
