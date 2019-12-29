package com.xml.megatravel.soap.converter;

import com.xml.megatravel.dto.AddressDto;
import com.xml.megatravel.dto.request.CreatePropertyRequest;
import com.xml.megatravel.model.Address;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.soap.model.address.XmlAddress;
import com.xml.megatravel.soap.model.property.CreatePropertyResponse;
import com.xml.megatravel.soap.model.property.GetPropertiesResponse;
import com.xml.megatravel.soap.model.property.XmlProperty;
import com.xml.megatravel.soap.model.propertyservice.XmlPropertyService;
import com.xml.megatravel.soap.model.service.XmlService;
import com.xml.megatravel.soap.model.type.XmlType;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xml.megatravel.soap.converter.XmlAgentConverter.toXmlAgentFromAgent;
import static com.xml.megatravel.soap.converter.XmlServiceConverter.toXmlServiceFromService;
import static com.xml.megatravel.soap.converter.XmlTypeConverter.toXmlTypeFromType;

public class XmlPropertyConverter {

    public static CreatePropertyRequest toCreatePropertyRequestFromXml(com.xml.megatravel.soap.model.property.CreatePropertyRequest request) {
        return CreatePropertyRequest.builder()
                .name(request.getName())
                .description(request.getDescription())
                .stars(request.getStars())
                .numberOfPeople(request.getNumberOfPeople())
                .numberOfCancellationDays(request.getNumberOfCancellationDays())
                .price(request.getPrice())
                .typeId(UUID.fromString(request.getTypeId()))
                .address(AddressDto.builder()
                        .street(request.getAddress().getStreet())
                        .country(request.getAddress().getCountry())
                        .city(request.getAddress().getCity())
                        .longitude(request.getAddress().getLongitude())
                        .latitude(request.getAddress().getLatitude())
                        .build())
                .agentId(UUID.fromString(request.getAgentId()))
                .services(request.getServices().stream().map(UUID::fromString).collect(Collectors.toList()))
                .build();
    }

    public static CreatePropertyResponse toCreatePropertyResponseFromProperty(Property property, HttpStatus status) {
        return CreatePropertyResponse.builder()
                .property(toXmlPropertyFromProperty(property))
                .status(status.value())
                .build();
    }

    public static GetPropertiesResponse toGetPropertiesResponseFromPropertiesList(List<Property> properties) {
        return GetPropertiesResponse.builder()
                .properties(properties.stream().map(XmlPropertyConverter::toXmlPropertyFromProperty).collect(Collectors.toList()))
                .build();
    }

    public static XmlProperty toXmlPropertyFromProperty(Property property) {
        final List<XmlPropertyService> propertyServices = property.getPropertyServices()
                .stream()
                .map(ps -> {
                    final XmlPropertyService xmlPropertyService = XmlPropertyService.builder()
                            .service(toXmlServiceFromService(ps.getService()))
                            .build();
                    xmlPropertyService.setId(ps.getId().toString());

                    return xmlPropertyService;
                }).collect(Collectors.toList());

        final XmlProperty xmlProperty = XmlProperty.builder()
                .name(property.getName())
                .description(property.getDescription())
                .price(property.getPrice())
                .numberOfCancellationDays(property.getNumberOfCancellationDays())
                .numberOfPeople(property.getNumberOfPeople())
                .stars(property.getStars())
                .type(toXmlTypeFromType(property.getType()))
                .agent(toXmlAgentFromAgent(property.getAgent()))
                .category(property.getCategory().name())
                .address(toXmlAddressFromAddress(property.getAddress()))
                .propertyServices(propertyServices)
                .reservations(Collections.emptyList())
                .build();

        xmlProperty.setId(property.getId().toString());

        return xmlProperty;
    }

    public static XmlAddress toXmlAddressFromAddress(Address address) {
        final XmlAddress xmlAddress = XmlAddress.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();

        xmlAddress.setId(address.getId().toString());

        return xmlAddress;
    }
}
