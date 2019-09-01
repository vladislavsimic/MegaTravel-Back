package com.xml.megatravel.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.RATING_COMMENT_SIZE;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
@Where(clause = "is_deleted='false'")
public class Rating extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @NotNull
    @PositiveOrZero
    @Column(name = "overall_rating")
    private Integer overallRating;

    @NotNull
    @PositiveOrZero
    @Column(name = "staff_rating")
    private Integer staffRating;

    @NotNull
    @PositiveOrZero
    @Column(name = "services_rating")
    private Integer servicesRating;

    @Size(max = RATING_COMMENT_SIZE)
    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "is_comment_reviewed")
    private Boolean isCommentReviewed;

    @NotNull
    @Column(name = "is_comment_approved")
    private Boolean isCommentApproved;
}
