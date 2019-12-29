package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.CreateReservationRequest;
import com.xml.megatravel.dto.response.ReservationResponse;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.enumeration.ReservationStatus;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.xml.megatravel.converter.PropertyConverter.toPropertyResponseFromProperty;
import static com.xml.megatravel.util.DateTimeUtil.DATE_TIME_FORMAT;

public class ReservationConverter {

    public static Reservation toReservationFromCreateReservationRequest(CreateReservationRequest request) {
        return Reservation.builder()
                .numberOfPeople(request.getNumberOfPeople())
                .price(request.getPrice())
                .endDate(request.getEndDate())
                .startDate(request.getStartDate())
                .reservationStatus(ReservationStatus.PENDING)
                .user(request.getUser())
                .property(request.getProperty())
                .build();
    }

    public static List<ReservationResponse> toReservationsResponseFromReservationsList(List<Reservation> reservations) {
        return reservations.stream().map(ReservationConverter::toReservationResponseFromReservation).collect(Collectors.toList());
    }

    public static ReservationResponse toReservationResponseFromReservation(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .price(reservation.getPrice())
                .endDate(reservation.getEndDate().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .startDate(reservation.getStartDate().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .numberOfPeople(reservation.getNumberOfPeople())
                .reservationStatus(reservation.getReservationStatus())
                .propertyResponse(toPropertyResponseFromProperty(reservation.getProperty(), Collections.emptyList()))
                .build();
    }

}
