package com.airbnb.payload;

import com.airbnb.entity.Image;
import com.airbnb.entity.Property;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ImageDto {
    private Property property;
    private List<String> images;

    public ImageDto(Property property,List<String> images) {
        this.property = property;
        this.images = images;
    }
}
