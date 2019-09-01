package com.xml.megatravel.soap.converter;

import com.xml.megatravel.model.Service;
import com.xml.megatravel.soap.model.service.GetServicesResponse;
import com.xml.megatravel.soap.model.service.XmlService;

import java.util.List;
import java.util.stream.Collectors;

public class XmlServiceConverter {

    public static GetServicesResponse toGetServicesResponseFromServicesList(List<Service> services) {
        return GetServicesResponse.builder()
                .services(services.stream().map(XmlServiceConverter::toXmlServiceFromService).collect(Collectors.toList()))
                .build();
    }

    public static XmlService toXmlServiceFromService(Service service) {
        final XmlService xmlService = XmlService.builder()
                .name(service.getName())
                .build();

        xmlService.setId(service.getId().toString());

        return xmlService;
    }
}
