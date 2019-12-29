package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreatePropertyRequest;
import com.xml.megatravel.dto.request.UpdatePropertyRequest;
import com.xml.megatravel.dto.response.PropertyResponse;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.Role;
import com.xml.megatravel.service.AgentService;
import com.xml.megatravel.service.ImageService;
import com.xml.megatravel.service.PropertyService;
import com.xml.megatravel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
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

    private final UserService userService;

    private final AgentService agentService;

    public PropertyController(PropertyService propertyService, ImageService imageService, UserService userService, AgentService agentService) {
        this.propertyService = propertyService;
        this.imageService = imageService;
        this.userService = userService;
        this.agentService = agentService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getProperties() {
        final List<Property> properties = propertyService.getProperties();
        final Map<UUID, List<Image>> images = imageService.getImagesForProperties(properties.stream().map(Property::getId).collect(Collectors.toList()));
        return ResponseEntity.ok(toPropertyResponseListFromPropertyList(properties, images));
    }

    @GetMapping(value = "/{id}/manager")
    public ResponseEntity<List<PropertyResponse>> getAgentProperties(@PathVariable("id") UUID id) {
        final List<Property> properties = propertyService.getAgentProperties(id);
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
    public ResponseEntity<IdWrapper> createProperty(@Valid @RequestBody CreatePropertyRequest request, @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final User user = userService.getUserById(principalId);
        final Agent agent;
        if(user.getRole() == Role.ADMIN) {
            agent = agentService.getAgentById(request.getAgentId());
        } else {
            agent = agentService.getAgentByUserId(principalId);
        }
        request.setAgentId(agent.getId());
        final Property property = propertyService.createProperty(request);
        return ResponseEntity.ok(IdWrapper.of(property.getId()));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT','ADMIN')")
    public ResponseEntity<Void> updateProperty(@PathVariable("id") UUID id, @Valid @RequestBody UpdatePropertyRequest request) {
        final Property property = propertyService.getProperty(id);
        propertyService.updateProperty(property, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('AGENT','ADMIN')")
    public ResponseEntity<Void> deleteProperty(@PathVariable("id") UUID id) {
        final Property property = propertyService.getProperty(id);
        propertyService.deleteProperty(property);
        return ResponseEntity.noContent().build();
    }
}
