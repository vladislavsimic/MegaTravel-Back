package com.xml.megatravel.service;

import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.User;
import com.xml.megatravel.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Transactional(readOnly = true)
    public Property getPropertyById(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(null);
    }

}
