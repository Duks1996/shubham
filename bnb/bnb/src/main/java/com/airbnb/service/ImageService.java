package com.airbnb.service;

import com.airbnb.entity.Image;
import com.airbnb.entity.Property;
import com.airbnb.payload.ImageDto;
import com.airbnb.repository.ImageRepository;
import com.airbnb.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final BucketService bucketService;
    private final ImageRepository imageRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public ImageService(BucketService bucketService, ImageRepository imageRepository, PropertyRepository propertyRepository) {
        this.bucketService = bucketService;
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
    }

    public Image uploadImageToProperty(MultipartFile file, String bucketName, long propertyId) throws IOException {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isEmpty()) {
            throw new IllegalArgumentException("Property with ID " + propertyId + " not found.");
        }

        String fileUrl = bucketService.uploadFile(file, bucketName);

        Image image = new Image();
        image.setUrl(fileUrl);
        image.setProperty(optionalProperty.get());
        return imageRepository.save(image);
    }

    public ImageDto viewImagesByPropertyId(long propertyId) throws IOException{
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isEmpty()) {
            throw new IllegalArgumentException("Property with ID " + propertyId + " not found.");
        }
        return new ImageDto(optionalProperty.get(),imageRepository.findByPropertyId(propertyId));
    }
}
