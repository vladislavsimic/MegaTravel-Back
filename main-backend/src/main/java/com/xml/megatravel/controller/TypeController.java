package com.xml.megatravel.controller;

import com.xml.megatravel.dto.request.TypeRequest;
import com.xml.megatravel.dto.response.TypeResponse;
import com.xml.megatravel.model.Type;
import com.xml.megatravel.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.TypeConverter.toListTypeResponseFromListType;
import static com.xml.megatravel.converter.TypeConverter.toTypeResponseFromType;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeResponse>> getTypes() {
        final List<Type> types = typeService.getTypes();
        return ResponseEntity.ok(toListTypeResponseFromListType(types));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT', 'ADMIN')")
    public ResponseEntity<TypeResponse> getType(@PathVariable(name = "id") UUID id) {
        final Type type = typeService.getTypeById(id);
        return ResponseEntity.ok(toTypeResponseFromType(type));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TypeResponse> createType(@Valid @RequestBody TypeRequest request) {
        final Type type = typeService.createType(request);
        return ResponseEntity.ok(toTypeResponseFromType(type));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TypeResponse> updateType(@PathVariable(name = "id") UUID id, @Valid @RequestBody TypeRequest request) {
        final Type type = typeService.updateType(id, request);
        return ResponseEntity.ok(toTypeResponseFromType(type));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteType(@PathVariable(name = "id") UUID id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
