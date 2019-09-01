package com.xml.megatravel.service;

import com.xml.megatravel.model.Service;
import com.xml.megatravel.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
