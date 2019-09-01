package com.xml.megatravel.repository;

import com.xml.megatravel.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    @Query("SELECT i FROM Image i " +
            "JOIN Property p ON i.entityId = p.id " +
            "AND i.entityType = com.xml.megatravel.model.enumeration.ImageType.PROPERTY " +
            "WHERE p.id = :propertyId ")
    List<Image> findAllByPropertyId(@Param("propertyId") UUID propertyId);
}
