package com.xml.megatravel.service;

import com.xml.megatravel.component.S3Client;
import com.xml.megatravel.model.BaseEntity;
import com.xml.megatravel.model.Image;
import com.xml.megatravel.model.Property;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.ImageType;
import com.xml.megatravel.repository.ImageRepository;
import com.xml.megatravel.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.xml.megatravel.util.FileUtils.getFileIdFromUrl;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    private final S3Client s3Client;

    public ImageService(ImageRepository imageRepository, UserRepository userRepository, S3Client s3Client) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.s3Client = s3Client;
    }

    @Transactional(rollbackFor = Exception.class)
    public Image createImageForProperty(Property property, String url) {
        final Image image = Image.builder()
                .entityId(property.getId())
                .entityType(ImageType.PROPERTY)
                .url(url)
                .build();

        imageRepository.save(image);

        return image;
    }

    @Transactional(readOnly = true)
    public List<Image> getPropertyImages(UUID propertyId) {
        return imageRepository.findAllByPropertyId(propertyId);
    }

    @Transactional(readOnly = true)
    public Map<UUID, List<Image>> getImagesForProperties(List<UUID> propertyIds) {
        final Map<UUID, List<Image>> images = new HashMap<>();

        for (UUID id : propertyIds) {
            final List<Image> propertyImages = getPropertyImages(id);
            images.put(id, propertyImages);
        }

        return images;
    }

    @Transactional(rollbackFor = Exception.class)
    public Image uploadUserProfilePicture(User user, MultipartFile imageFile) {
        final Image oldPicture = user.getProfilePicture();
        final List<Image> pendingChanges = new ArrayList<>();
        if(oldPicture != null) {
            pendingChanges.add(oldPicture);
            s3Client.delete(getFileIdFromUrl(oldPicture.getUrl()));
            oldPicture.setIsDeleted(true);
        }

        final Image newPicture = createImageForEntity(imageFile, user, ImageType.PROFILE_PICTURE);
        pendingChanges.add(newPicture);
        imageRepository.saveAll(pendingChanges);

        user.setProfilePicture(newPicture);

        userRepository.save(user);
        return newPicture;
    }

    @Transactional(rollbackFor = Exception.class)
    public Image uploadPropertyPicture(Property property, MultipartFile imageFile) {
        final Image image = createImageForEntity(imageFile, property, ImageType.PROPERTY);

        imageRepository.save(image);
        return image;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Image> uploadPropertyPictures(Property property, List<MultipartFile> imageFiles) {
        final List<Image> images = new ArrayList<>();

        for (MultipartFile file : imageFiles) {
            final Image image = createImageForEntity(file, property, ImageType.PROPERTY);
            images.add(image);
        }

        imageRepository.saveAll(images);

        return images;
    }

    private Image createImageForEntity(MultipartFile image, BaseEntity entity, ImageType imageType) {
        final String imageUrl = s3Client.upload(image);

        return Image.builder()
                .entityId(entity.getId())
                .entityType(imageType)
                .url(imageUrl)
                .build();
    }

}
