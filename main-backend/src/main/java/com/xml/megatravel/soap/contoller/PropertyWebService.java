package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.model.Property;
import com.xml.megatravel.service.PropertyService;
import com.xml.megatravel.soap.model.property.CreatePropertyRequest;
import com.xml.megatravel.soap.model.property.CreatePropertyResponse;
import com.xml.megatravel.soap.model.property.GetPropertiesRequest;
import com.xml.megatravel.soap.model.property.GetPropertiesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static com.xml.megatravel.soap.converter.XmlPropertyConverter.*;


@Endpoint
public class PropertyWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/property";

    private final PropertyService propertyService;

    public PropertyWebService(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPropertiesRequest")
    @ResponsePayload
    public GetPropertiesResponse getProperties(@RequestPayload GetPropertiesRequest request) {
        final List<Property> properties = propertyService.getPropertiesForAgent();
        return toGetPropertiesResponseFromPropertiesList(properties);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPropertyRequest")
    @ResponsePayload
    public CreatePropertyResponse createProperty(@RequestPayload CreatePropertyRequest request) {
        final Property property = propertyService.createProperty(toCreatePropertyRequestFromXml(request));
        return toCreatePropertyResponseFromProperty(property, HttpStatus.OK);
    }


}
