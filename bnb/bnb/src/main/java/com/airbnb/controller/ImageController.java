package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Image;
import com.airbnb.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @PathVariable String bucketName,
                                         @PathVariable long propertyId,
                                         @AuthenticationPrincipal AppUser appUser) {
        try {
            Image uploadedImage = imageService.uploadImageToProperty(file, bucketName, propertyId);
            return new ResponseEntity<>(uploadedImage, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Upload failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
