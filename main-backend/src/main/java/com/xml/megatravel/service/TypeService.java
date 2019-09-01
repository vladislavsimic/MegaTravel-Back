package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.TypeRequest;
import com.xml.megatravel.exception.DeletionException;
import com.xml.megatravel.exception.EntityAlreadyExistsException;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Type;
import com.xml.megatravel.repository.PropertyRepository;
import com.xml.megatravel.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.TypeConverter.toTypeFromTypeRequest;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    private final PropertyRepository propertyRepository;

    public TypeService(TypeRepository typeRepository, PropertyRepository propertyRepository) {
        this.typeRepository = typeRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    public Type getTypeById(UUID id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type not found with given id: " + id.toString()));
    }

    public Type createType(TypeRequest request) {
        if(typeRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException("Type with given name already exists.");
        }

        final Type type = toTypeFromTypeRequest(request);

        typeRepository.save(type);

        return type;
    }

    public Type updateType(UUID id, TypeRequest request) {
        final Type type = typeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type not found with given id: " + id.toString()));

        type.setName(request.getName());

        typeRepository.save(type);

        return type;
    }

    public void deleteType(UUID id) {
        if(propertyRepository.existsByTypeId(id)) {
            throw new DeletionException("Properties with given Type still exist, can't delete it.");
        }

        final Type type = typeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type not found with given id: " + id.toString()));

        type.setIsDeleted(true);

        typeRepository.save(type);
    }
}
