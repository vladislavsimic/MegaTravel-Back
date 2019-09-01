package com.xml.megatravel.converter;

import com.xml.megatravel.dto.response.PropertyResponse;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.AddressConverter.toAddressDtoFromAddress;

public class PropertyConverter {


    public static PropertyResponse toPropertyResponseFromProperty(Property property, List<Image> images) {
        final Map<UUID, String> services = new HashMap<>();

        property.getPropertyServices().forEach(ps -> services.put(ps.getService().getId(), ps.getService().getName()));

        return PropertyResponse.builder()
                .id(property.getId())
                .agentId(property.getAgent().getId())
                .category(property.getCategory())
                .name(property.getName())
                .description(property.getDescription())
                .stars(property.getStars())
                .type(property.getType().getName())
                .autumnPrice(property.getAutumnPrice())
                .summerPrice(property.getSummerPrice())
                .winterPrice(property.getWinterPrice())
                .springPrice(property.getSpringPrice())
                .numberOfCancellationDays(property.getNumberOfCancellationDays())
                .numberOfPeople(property.getNumberOfPeople())
                .createdAt(property.getTimeCreated())
                .address(toAddressDtoFromAddress(property.getAddress()))
                .propertyServices(services)
                .imageUrls(images.stream().map(Image::getUrl).collect(Collectors.toList()))
                .build();
    }

}
