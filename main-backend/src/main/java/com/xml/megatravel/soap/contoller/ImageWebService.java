package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.service.ImageService;
import com.xml.megatravel.service.PropertyService;
import com.xml.megatravel.soap.model.image.UploadImageForPropertyRequest;
import com.xml.megatravel.soap.model.image.UploadImageForPropertyResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class ImageWebService {

    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/property";

    private final PropertyService propertyService;

    private final ImageService imageService;

    public ImageWebService(PropertyService propertyService, ImageService imageService) {
        this.propertyService = propertyService;
        this.imageService = imageService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "uploadImageForPropertyRequest")
    @ResponsePayload
    public UploadImageForPropertyResponse uploadImageForProperty(@RequestPayload UploadImageForPropertyRequest request) {
        imageService.createImageForProperty(propertyService.getProperty(UUID.fromString(request.getPropertyId())), request.getUrl());
        return new UploadImageForPropertyResponse();
    }
}
