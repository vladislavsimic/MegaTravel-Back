package com.xml.megatravel.controller;

import com.xml.megatravel.dto.request.ServiceRequest;
import com.xml.megatravel.dto.response.ServiceResponse;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.ServiceConverter.toListServiceResponseFromListService;
import static com.xml.megatravel.converter.ServiceConverter.toServiceResponseFromService;

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

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServiceResponse> createService(@Valid @RequestBody ServiceRequest request) {
        final Service service = serviceService.createService(request);
        return ResponseEntity.ok(toServiceResponseFromService(service));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable(name = "id") UUID id, @Valid @RequestBody ServiceRequest request) {
        final Service service = serviceService.updateService(id, request);
        return ResponseEntity.ok(toServiceResponseFromService(service));
    }

}
