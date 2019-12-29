package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.ServiceRequest;
import com.xml.megatravel.exception.EntityAlreadyExistsException;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.ServiceConverter.toServiceFromServiceRequest;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional(readOnly = true)
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    public Service createService(ServiceRequest request) {
        if(serviceRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException("Service with given name already exists.");
        }

        final Service service = toServiceFromServiceRequest(request);

        serviceRepository.save(service);

        return service;
    }

    public Service updateService(UUID id, ServiceRequest request) {
        final Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with given id: " + id.toString()));

        service.setName(request.getName());

        serviceRepository.save(service);

        return service;
    }

}
