package com.xml.megatravel.dto.request;

import com.xml.megatravel.model.enumeration.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReservationRequest {

    @Positive
    private Integer numberOfPeople;

    @NotNull
    private ReservationStatus reservationStatus;
}
