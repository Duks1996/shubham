package com.airbnb.service;

import com.airbnb.entity.Property;

import java.util.List;

public interface PropertyService {
    List<Property> searchProperty(String name);
    Property addProperty(Property property);
    Property updateProperty(Long id, Property property);
    String deleteProperty(Long id);
}
