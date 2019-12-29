package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.CreatePropertyRequest;
import com.xml.megatravel.dto.response.PropertyResponse;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;

import java.util.*;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.AddressConverter.toAddressDtoFromAddress;

public class PropertyConverter {

    public static List<PropertyResponse> toPropertyResponseListFromPropertyList(List<Property> properties, Map<UUID, List<Image>> images) {
        return properties.stream().map(p -> toPropertyResponseFromProperty(p, images.get(p.getId()))).collect(Collectors.toList());
    }

    public static PropertyResponse toPropertyResponseFromProperty(Property property, List<Image> images) {
//        final List<String> services = property.getPropertyServices().forEach(ps -> ps.getService().getName());

        final List<String> services = property.getPropertyServices().stream()
                .map(ps -> ps.getService().getName())
                .collect(Collectors.toList());

        return PropertyResponse.builder()
                .id(property.getId())
                .agentId(property.getAgent().getId())
                .category(property.getCategory())
                .name(property.getName())
                .description(property.getDescription())
                .stars(property.getStars())
                .type(property.getType().getId())
                .price(property.getPrice())
                .numberOfCancellationDays(property.getNumberOfCancellationDays())
                .numberOfPeople(property.getNumberOfPeople())
                .createdAt(property.getTimeCreated())
                .address(toAddressDtoFromAddress(property.getAddress()))
                .propertyServices(services)
                .imageUrls(images.stream().map(Image::getUrl).collect(Collectors.toList()))
                .build();
    }

    public static Property toPropertyFromCreatePropertyRequest(CreatePropertyRequest request) {
        return Property.builder()
                .name(request.getName())
                .description(request.getDescription())
                .stars(request.getStars())
                .numberOfPeople(request.getNumberOfPeople())
                .numberOfCancellationDays(request.getNumberOfCancellationDays())
                .price(request.getPrice())
                .build();
    }
}
