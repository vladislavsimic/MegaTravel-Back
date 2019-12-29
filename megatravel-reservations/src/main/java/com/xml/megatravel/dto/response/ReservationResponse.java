package com.xml.megatravel.dto.response;

import com.xml.megatravel.model.enumeration.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class ReservationResponse {

    private UUID id;

    private ReservationStatus reservationStatus;

    private Double price;

    private PropertyResponse propertyResponse;

    private String startDate;

    private String endDate;

    private Integer numberOfPeople;
}
