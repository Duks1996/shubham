package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{
    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> searchProperty(String name) {
        return propertyRepository.searchProperty(name);
    }

    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        existing.setName(property.getName());
        existing.setNumber_of_guests(property.getNumber_of_guests());
        existing.setNumber_of_beds(property.getNumber_of_beds());
        existing.setNumber_of_bedrooms(property.getNumber_of_bedrooms());
        existing.setNumber_of_bathrooms(property.getNumber_of_bathrooms());
        existing.setCity(property.getCity());
        existing.setCountry(property.getCountry());
        return propertyRepository.save(existing);
    }

    @Override
    public String deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            return "Property not found";
        }else {
            propertyRepository.deleteById(id);
            return "Property deleted";
        }
    }
}
