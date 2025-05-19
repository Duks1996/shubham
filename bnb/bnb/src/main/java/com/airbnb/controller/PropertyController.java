package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyRepository pr;

    public PropertyController(PropertyRepository pr) {
        this.pr = pr;
    }

    @GetMapping("/searchproperty")
    public List<Property> searchProperty(@RequestParam String name){
        return pr.searchProperty(name);
    }
}
