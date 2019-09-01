package com.xml.megatravel.converter;

import com.xml.megatravel.dto.response.ImageResponse;
import com.xml.megatravel.model.Image;

public class ImageConverter {

    public static ImageResponse toImageResponseFromImage(Image image){
        return ImageResponse.builder()
                .id(image.getId())
                .url(image.getUrl())
                .entityId(image.getEntityId())
                .entityType(image.getEntityType())
                .build();
    }
}
