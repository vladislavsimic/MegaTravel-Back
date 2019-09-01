package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.ServiceRequest;
import com.xml.megatravel.exception.DeletionException;
import com.xml.megatravel.exception.EntityAlreadyExistsException;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.repository.PropertyServiceRepository;
import com.xml.megatravel.repository.ServiceRepository;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.ServiceConverter.toServiceFromServiceRequest;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    private final PropertyServiceRepository propertyServiceRepository;

    public ServiceService(ServiceRepository serviceRepository, PropertyServiceRepository propertyServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.propertyServiceRepository = propertyServiceRepository;
    }

    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(UUID id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with given id: " + id.toString()));
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

    public void deleteService(UUID id) {
        if(propertyServiceRepository.existsByServiceId(id)) {
            throw new DeletionException("Service is still available in some Properties, can't delete it.");
        }

        final Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with given id: " + id.toString()));
        service.setIsDeleted(true);

        serviceRepository.save(service);
    }
}
