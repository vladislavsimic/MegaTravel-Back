package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.CreateRatingRequest;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.exception.ForbiddenOperationException;
import com.xml.megatravel.model.Rating;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.RatingConverter.toRatingFromCreateRatingRequest;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void approveComment(UUID ratingId) {
        final Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating with given id not found"));

        rating.setIsCommentReviewed(true);
        rating.setIsCommentApproved(true);

        ratingRepository.save(rating);
    }

    @Transactional(readOnly = true)
    public List<Rating> getRatings() {
        return ratingRepository.findAllByIsCommentReviewedFalse();
    }

    @Transactional(rollbackFor = Exception.class)
    public void denyComment(UUID ratingId) {
        final Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating with given id not found"));

        rating.setIsCommentReviewed(true);
        rating.setIsCommentApproved(false);

        ratingRepository.save(rating);
    }

    @Transactional(rollbackFor = Exception.class)
    public Rating createRating(CreateRatingRequest request, Reservation reservation, UUID userId) {
        if(!reservation.getUser().getId().equals(userId)) {
           throw new ForbiddenOperationException("Reservation not found for this user!");
        }

        if(reservation.getRating() != null) {
            throw new ForbiddenOperationException("Reservation has already received rating!");
        }

        final Rating rating = toRatingFromCreateRatingRequest(request, reservation);
        rating.setIsCommentApproved(false);
        rating.setIsCommentReviewed(false);

        ratingRepository.save(rating);

        return rating;
    }


    @Transactional(readOnly = true)
    public List<Rating> getByPropertyId(UUID propertyId) {
        return ratingRepository.findAllByReservationPropertyId(propertyId);
    }

    @Transactional(readOnly = true)
    public Rating getByReservationId(UUID reservationId) {
        return ratingRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Rating not yet left for this reservation"));
    }
}
