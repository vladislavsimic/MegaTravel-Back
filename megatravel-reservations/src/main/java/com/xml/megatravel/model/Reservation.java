package com.xml.megatravel.model;

import com.xml.megatravel.model.enumeration.ReservationStatus;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
@Where(clause = "is_deleted='false'")
public class Reservation extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "rating_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "cancellation_price")
    private Double cancellationPrice;

    public void decreaseCancelReservationPrice(double discountPercentage) {
        price -= price * discountPercentage;
    }

    public void createDiscount(double discountPercents) {
        price -= price * discountPercents;
    }
}
