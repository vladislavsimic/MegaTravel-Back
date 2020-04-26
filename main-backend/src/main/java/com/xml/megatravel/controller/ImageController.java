package com.xml.megatravel.controller;

import com.xml.megatravel.converter.ImageConverter;
import com.xml.megatravel.dto.response.ImageResponse;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.User;
import com.xml.megatravel.service.ImageService;
import com.xml.megatravel.service.PropertyService;
import com.xml.megatravel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.ImageConverter.toImageResponseFromImage;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    private final UserService userService;

    private final PropertyService propertyService;

    public ImageController(ImageService imageService, UserService userService, PropertyService propertyService) {
        this.imageService = imageService;
        this.userService = userService;
        this.propertyService = propertyService;
    }


    @PostMapping(value = "/profile-picture")
    @PreAuthorize("hasAnyAuthority('USER', 'AGENT', 'ADMIN')")
    public ResponseEntity<ImageResponse> uploadUserImage(@Valid @RequestParam(name = "image") MultipartFile image,
                                                         @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final User user = userService.getUserById(principalId);
        final Image profilePicture = imageService.uploadUserProfilePicture(user, image);

        return ResponseEntity.ok(toImageResponseFromImage(profilePicture));
    }

    @PostMapping(value = "/properties/{id}")
    public ResponseEntity<ImageResponse> uploadPropertyImage(@PathVariable("id") UUID propertyId,
                                                             @Valid @RequestParam(name = "image") MultipartFile image) {
        // TODO: remove this endpoint once images tested and working
        final Property property = propertyService.getProperty(propertyId);
        final Image propertyImage = imageService.uploadPropertyPicture(property, image);

        return ResponseEntity.ok(toImageResponseFromImage(propertyImage));
    }

    @PostMapping(value = "/properties/{id}/many")
    public ResponseEntity<List<ImageResponse>> uploadPropertyImages(@PathVariable("id") UUID propertyId,
                                                                    @Valid @RequestParam(name = "images") List<MultipartFile> images) {
        final Property property = propertyService.getProperty(propertyId);
        final List<Image> propertyImages = imageService.uploadPropertyPictures(property, images);

        return ResponseEntity.ok(propertyImages.stream().map(ImageConverter::toImageResponseFromImage).collect(Collectors.toList()));
    }

}
