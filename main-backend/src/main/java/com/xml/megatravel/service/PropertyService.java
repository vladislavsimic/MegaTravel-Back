package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.CreatePropertyRequest;
import com.xml.megatravel.dto.request.UpdatePropertyRequest;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.Service;
import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.model.enumeration.SortCriteria;
import com.xml.megatravel.repository.PropertyRepository;
import com.xml.megatravel.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.AddressConverter.toAddressFromAddressDto;
import static com.xml.megatravel.converter.PropertyConverter.toPropertyFromCreatePropertyRequest;

@org.springframework.stereotype.Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final ServiceRepository serviceRepository;

    private final PropertyServiceService propertyServiceService;

    private final AddressService addressService;

    private final AgentService agentService;

    private final TypeService typeService;

    public PropertyService(PropertyRepository propertyRepository, ServiceRepository serviceRepository, PropertyServiceService propertyServiceService, AddressService addressService, AgentService agentService, TypeService typeService) {
        this.propertyRepository = propertyRepository;
        this.serviceRepository = serviceRepository;
        this.propertyServiceService = propertyServiceService;
        this.addressService = addressService;
        this.agentService = agentService;
        this.typeService = typeService;
    }

    @Transactional(readOnly = true)
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Property> getPropertiesForAgent() {
        return propertyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Property> getAgentProperties(UUID id){
        return propertyRepository.findAllByAgentUserId(id);
    }

    @Transactional(readOnly = true)
    public Property getProperty(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property with given Id could not be found!"));
    }

    @Transactional(rollbackFor = Exception.class)
    public Property createProperty(CreatePropertyRequest request) {
        final Property property = toPropertyFromCreatePropertyRequest(request);
        property.setCategory(Category.NONE);
        property.setAddress(addressService.createAddress(toAddressFromAddressDto(request.getAddress())));
        property.setAgent(agentService.getAgentById(request.getAgentId()));
        property.setType(typeService.getTypeById(request.getTypeId()));

        propertyRepository.save(property);

        final List<Service> services = serviceRepository.findAllById(request.getServices());
        property.setPropertyServices(new HashSet<>(propertyServiceService.createPropertyServices(property, services)));

         return property;
    }

    @Transactional(rollbackFor = Exception.class)
    public Property updateProperty(Property property, UpdatePropertyRequest request) {
        property.setName(request.getName());
        property.setDescription(request.getDescription());
        property.setCategory(request.getCategory());
        property.setPrice(request.getPrice());
        property.setNumberOfPeople(request.getNumberOfPeople());
        property.setNumberOfCancellationDays(request.getNumberOfCancellationDays());
        property.setStars(request.getStars());
        property.setType(typeService.getTypeById(request.getTypeId()));

        propertyRepository.save(property);

        final List<Service> services = serviceRepository.findAllById(request.getServices());
        //final List<Service> servicesToRemove = serviceRepository.findAllById(request.getRemovedServices());
        propertyServiceService.updatePropertyServices(property, services);

        return property;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProperty(Property property) {
        // TODO: check for reservations etc

        property.setIsDeleted(true);

        propertyRepository.save(property);
    }
}
