package com.xml.megatravel.controller;

import com.xml.megatravel.dto.response.ServiceResponse;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.xml.megatravel.converter.ServiceConverter.toListServiceResponseFromListService;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getServices() {
        final List<Service> services = serviceService.getServices();
        return ResponseEntity.ok(toListServiceResponseFromListService(services));
    }

}
