package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreateRatingRequest;
import com.xml.megatravel.dto.response.RatingResponse;
import com.xml.megatravel.model.Rating;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.service.RatingService;
import com.xml.megatravel.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.RatingConverter.toRatingResponseFromRating;
import static com.xml.megatravel.converter.RatingConverter.toRatingResponseListFromRatingList;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    private final ReservationService reservationService;

    public RatingController(RatingService ratingService, ReservationService reservationService) {
        this.ratingService = ratingService;
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/{reservationId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<IdWrapper> createRating(@PathVariable("reservationId") UUID reservationId,
                                                  @Valid @RequestBody CreateRatingRequest request,
                                                  @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final Reservation reservation = reservationService.getReservationById(reservationId);
        final Rating rating = ratingService.createRating(request, reservation, principalId);
        return ResponseEntity.ok(IdWrapper.of(rating.getId()));
    }

    @PatchMapping(value = "/{ratingId}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> approveComment(@PathVariable("ratingId")UUID ratingId) {
        ratingService.approveComment(ratingId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{ratingId}/deny")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> denyComment(@PathVariable("ratingId")UUID ratingId) {
        ratingService.denyComment(ratingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RatingResponse>> getRatings() {
        final List<Rating> ratings = ratingService.getRatings();
        return ResponseEntity.ok(toRatingResponseListFromRatingList(ratings));
    }

    @GetMapping(value = "/property/{propertyId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByProperty(@PathVariable("propertyId") UUID propertyId) {
        final List<Rating> ratings = ratingService.getByPropertyId(propertyId);
        return ResponseEntity.ok(toRatingResponseListFromRatingList(ratings));
    }

    @GetMapping(value = "/{reservationId}")
    @PreAuthorize("hasAnyAuthority('USER', 'AGENT')")
    public ResponseEntity<RatingResponse> getRatingForReservation(@PathVariable("reservationId") UUID reservationId) {
        final Rating rating = ratingService.getByReservationId(reservationId);
        return ResponseEntity.ok(toRatingResponseFromRating(rating));
    }

}
