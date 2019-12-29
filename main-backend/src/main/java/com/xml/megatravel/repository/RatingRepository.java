package com.xml.megatravel.repository;

import com.xml.megatravel.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    List<Rating> findAllByReservationPropertyId(UUID propertyId);

    List<Rating> findAllByReservationPropertyIdAndIsCommentApprovedTrueAndIsCommentReviewedTrue(UUID propertyId);

    Optional<Rating> findByReservationId(UUID reservationId);

    List<Rating> findAllByIsCommentReviewedFalse();
}
