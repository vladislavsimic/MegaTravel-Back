package com.xml.megatravel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.RATING_COMMENT_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRatingRequest {

    @NotNull
    @PositiveOrZero
    private Integer overallRating;

    @NotNull
    @PositiveOrZero
    private Integer staffRating;

    @NotNull
    @PositiveOrZero
    private Integer servicesRating;

    @Size(max = RATING_COMMENT_SIZE)
    private String comment;
}
