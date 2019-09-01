package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.model.Service;
import com.xml.megatravel.service.ServiceService;
import com.xml.megatravel.soap.model.service.GetServicesRequest;
import com.xml.megatravel.soap.model.service.GetServicesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static com.xml.megatravel.soap.converter.XmlServiceConverter.toGetServicesResponseFromServicesList;

@Endpoint
public class ServiceWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/service";

    private final ServiceService serviceService;

    public ServiceWebService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getServicesRequest")
    @ResponsePayload
    public GetServicesResponse getServices(@RequestPayload GetServicesRequest request) {
        final List<Service> services = serviceService.getServices();
        return toGetServicesResponseFromServicesList(services);
    }
}
