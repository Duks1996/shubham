package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        return new ResponseEntity<>(propertyService.addProperty(property), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Property> updateProperty(@RequestParam Long id, @RequestBody Property property) {
        return new ResponseEntity<>(propertyService.updateProperty(id, property), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProperty(@RequestParam Long id) {
        return new ResponseEntity<>(propertyService.deleteProperty(id),HttpStatus.OK);
    }
}
