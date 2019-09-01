package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.User;
import com.xml.megatravel.service.ReservationService;
import com.xml.megatravel.service.UserService;
import com.xml.megatravel.soap.model.reservation.*;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.soap.converter.XmlReservationConverter.*;

@Endpoint
public class ReservationWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/reservation";

    private final UserService userService;

    private final ReservationService reservationService;

    public ReservationWebService(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationsRequest")
    @ResponsePayload
    public GetReservationsResponse getReservations(@RequestPayload GetReservationsRequest request) {
        final List<Reservation> reservations = reservationService.getReservations();
        return toGetReservationsResponseFromReservationsList(reservations);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createReservationRequest")
    @ResponsePayload
    public CreateReservationResponse createReservation(@RequestPayload CreateReservationRequest request) {
        final User user = userService.getUserById(UUID.fromString(request.getUserId()));
        final Reservation reservation = reservationService.createReservation(toCreateReservationRequestFromXmlCreateReservationRequest(request, user));
        return toCreateReservationResponseFromReservation(reservation, HttpStatus.OK);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateReservationStatusRequest")
    @ResponsePayload
    public UpdateReservationStatusResponse updateReservationsStatus(@RequestPayload UpdateReservationStatusRequest request) {
        final Reservation reservation = reservationService.getReservationById(UUID.fromString(request.getReservationId()));
        final boolean success = reservationService.updateReservationStatus(reservation, request.getStatus());

        return UpdateReservationStatusResponse.builder()
                .statusCode(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
