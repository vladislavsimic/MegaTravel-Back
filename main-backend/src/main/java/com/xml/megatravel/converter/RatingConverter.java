package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.CreateRatingRequest;
import com.xml.megatravel.dto.response.RatingResponse;
import com.xml.megatravel.model.Rating;
import com.xml.megatravel.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class RatingConverter {

    public static List<RatingResponse> toRatingResponseListFromRatingList(List<Rating> ratings) {
        return ratings.stream().map(RatingConverter::toRatingResponseFromRating).collect(Collectors.toList());
    }

    public static RatingResponse toRatingResponseFromRating(Rating rating) {
        return RatingResponse.builder()
                .id(rating.getId())
                .comment(rating.getComment())
                .overallRating(rating.getOverallRating())
                .servicesRating(rating.getServicesRating())
                .staffRating(rating.getStaffRating())
                .isCommentApproved(rating.getIsCommentApproved())
                .isCommentReviewed(rating.getIsCommentReviewed())
                .build();
    }

    public static Rating toRatingFromCreateRatingRequest(CreateRatingRequest request, Reservation reservation) {
        return Rating.builder()
                .comment(request.getComment())
                .overallRating(request.getOverallRating())
                .servicesRating(request.getServicesRating())
                .staffRating(request.getStaffRating())
                .reservation(reservation)
                .build();
    }
}
