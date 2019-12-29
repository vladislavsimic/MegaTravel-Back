package com.xml.megatravel.service;

import com.xml.megatravel.model.BaseEntity;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.PropertyService;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.repository.PropertyServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
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
    public void updatePropertyServices(Property property, List<Service> services) {

        final List<com.xml.megatravel.model.PropertyService> propertyServices =
                propertyServiceRepository.findAllById(
                        property.getPropertyServices()
                                .stream()
                                .map(BaseEntity::getId)
                                .collect(Collectors.toList())
                );

        List<UUID> serviceIds = services.stream().map(BaseEntity::getId).collect(Collectors.toList());
        List<UUID> propServicesIds = new ArrayList<UUID>();

        for(com.xml.megatravel.model.PropertyService ps:propertyServices){
            propServicesIds.add(ps.getService().getId());
        }

        final List<com.xml.megatravel.model.PropertyService> servicesToRemove = new ArrayList<com.xml.megatravel.model.PropertyService>();
        final List<com.xml.megatravel.model.Service> servicesToAdd = new ArrayList<com.xml.megatravel.model.Service>();

        for (PropertyService ps : propertyServices) {
            if(!serviceIds.contains(ps.getService().getId())){
                servicesToRemove.add(ps);
            }
        }

        for(Service s:services){
            if(!propServicesIds.contains(s.getId())){
                servicesToAdd.add(s);
            }
        }

        if(!servicesToRemove.isEmpty()) {

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
