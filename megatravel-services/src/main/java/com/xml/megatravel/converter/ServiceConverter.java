package com.xml.megatravel.converter;

import com.xml.megatravel.dto.response.ServiceResponse;
import com.xml.megatravel.model.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceConverter {

    public static List<ServiceResponse> toListServiceResponseFromListService(List<Service> services) {
        return services.stream().map(ServiceConverter::toServiceResponseFromService).collect(Collectors.toList());
    }

    public static ServiceResponse toServiceResponseFromService(Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .build();
    }
}
