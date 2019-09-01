package com.xml.megatravel.service;

import com.xml.megatravel.model.BaseEntity;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.repository.PropertyServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class PropertyServiceService {

    private PropertyServiceRepository propertyServiceRepository;

    public PropertyServiceService(PropertyServiceRepository propertyServiceRepository) {
        this.propertyServiceRepository = propertyServiceRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<com.xml.megatravel.model.PropertyService> createPropertyServices(Property property, List<Service> services) {
        final List<com.xml.megatravel.model.PropertyService> propertyServices = new ArrayList<>();
        for (Service s : services) {
            propertyServices.add(new com.xml.megatravel.model.PropertyService(property, s));
        }

        propertyServiceRepository.saveAll(propertyServices);

        return propertyServices;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePropertyServices(Property property, List<Service> servicesToAdd, List<Service> servicesToRemove) {
        if(!servicesToRemove.isEmpty()) {
            final List<com.xml.megatravel.model.PropertyService> propertyServices =
                    propertyServiceRepository.findAllById(
                            property.getPropertyServices()
                                    .stream()
                                    .map(BaseEntity::getId)
                                    .collect(Collectors.toList())
                    );

            propertyServiceRepository.deleteInBatch(propertyServices.stream()
                    .filter(ps -> servicesToRemove.contains(ps.getService()))
                    .collect(Collectors.toList())
            );
        }

        if(!servicesToAdd.isEmpty()) {
            createPropertyServices(property, servicesToAdd);
        }
    }
}
