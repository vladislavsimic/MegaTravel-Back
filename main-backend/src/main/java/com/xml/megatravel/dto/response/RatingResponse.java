package com.xml.megatravel.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RatingResponse {

    private UUID id;

    private Integer overallRating;

    private Integer staffRating;

    private Integer servicesRating;

    private String comment;

    private Boolean isCommentReviewed;

    private Boolean isCommentApproved;
}
