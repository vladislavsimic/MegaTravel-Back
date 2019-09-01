package com.xml.megatravel.controller;

import com.xml.megatravel.dto.request.ServiceRequest;
import com.xml.megatravel.dto.response.ServiceResponse;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.xml.megatravel.converter.ServiceConverter.toServiceResponseFromService;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT', 'ADMIN')")
    public ResponseEntity<ServiceResponse> getService(@PathVariable(name = "id") UUID id) {
        final Service service = serviceService.getServiceById(id);
        return ResponseEntity.ok(toServiceResponseFromService(service));
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

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteService(@PathVariable(name = "id") UUID id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
