package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping("/searchproperty")
    public ResponseEntity<List<Property>> searchProperty(@RequestParam String name){
        List<Property> properties = propertyService.searchProperty(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
