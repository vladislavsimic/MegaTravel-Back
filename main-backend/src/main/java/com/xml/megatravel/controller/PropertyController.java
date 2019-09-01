package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreatePropertyRequest;
import com.xml.megatravel.dto.request.UpdatePropertyRequest;
import com.xml.megatravel.dto.response.PropertyResponse;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.enumeration.SortCriteria;
import com.xml.megatravel.service.ImageService;
import com.xml.megatravel.service.PropertyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.PropertyConverter.toPropertyResponseFromProperty;
import static com.xml.megatravel.converter.PropertyConverter.toPropertyResponseListFromPropertyList;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    private final ImageService imageService;

    public PropertyController(PropertyService propertyService, ImageService imageService) {
        this.propertyService = propertyService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getProperties(@RequestParam(name = "city") String city,
                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)@RequestParam(name = "startDate") LocalDateTime startDate,
                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(name = "endDate") LocalDateTime endDate,
                                                                @RequestParam(name = "numberOfPeople") Integer numberOfPeople,
                                                                @RequestParam(name = "typeId", required = false) UUID typeId,
                                                                @RequestParam(name = "stars", required = false) Integer stars ,
                                                                @RequestParam(name = "distance", required = false) Double distance,
                                                                @RequestParam(name = "serviceIds", required = false) List<UUID> serviceIds,
                                                                @RequestParam(name = "freeCancel", required = false) Boolean freeCancel,
                                                                @RequestParam(name = "daysToCancel", required = false) Integer daysToCancel,
                                                                @RequestParam(name = "sortCriteria") SortCriteria sortCriteria) {
        final List<Property> properties = propertyService.getProperties(city, startDate, endDate, numberOfPeople, typeId, stars, distance, serviceIds, freeCancel, daysToCancel, sortCriteria);
        final Map<UUID, List<Image>> images = imageService.getImagesForProperties(properties.stream().map(Property::getId).collect(Collectors.toList()));
        return ResponseEntity.ok(toPropertyResponseListFromPropertyList(properties, images));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PropertyResponse> getProperty(@PathVariable("id") UUID id) {
        final Property property = propertyService.getProperty(id);
        final List<Image> images = imageService.getPropertyImages(id);
        return ResponseEntity.ok(toPropertyResponseFromProperty(property, images));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<IdWrapper> createProperty(@Valid @RequestBody CreatePropertyRequest request) {
        final Property property = propertyService.createProperty(request);
        return ResponseEntity.ok(IdWrapper.of(property.getId()));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<Void> updateProperty(@PathVariable("id") UUID id, @Valid @RequestBody UpdatePropertyRequest request) {
        final Property property = propertyService.getProperty(id);
        propertyService.updateProperty(property, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<Void> deleteProperty(@PathVariable("id") UUID id) {
        final Property property = propertyService.getProperty(id);
        propertyService.deleteProperty(property);
        return ResponseEntity.noContent().build();
    }
}
