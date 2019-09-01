package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreateReservationRequest;
import com.xml.megatravel.dto.request.UpdateReservationRequest;
import com.xml.megatravel.dto.response.ReservationResponse;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.User;
import com.xml.megatravel.service.ReservationService;
import com.xml.megatravel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.ReservationConverter.toReservationResponseFromReservation;
import static com.xml.megatravel.converter.ReservationConverter.toReservationsResponseFromReservationsList;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ReservationResponse>> getUserReservations(@ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final List<Reservation> reservations = reservationService.getReservationsByUserId(principalId);
        return ResponseEntity.ok(toReservationsResponseFromReservationsList(reservations));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ReservationResponse> getUserReservation(@PathVariable("id") UUID id,
                                                                  @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final Reservation reservation = reservationService.getReservationByUserId(id, principalId);
        return ResponseEntity.ok(toReservationResponseFromReservation(reservation));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<IdWrapper> createReservation(@Valid @RequestBody CreateReservationRequest request,
                                                       @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final User user = userService.getUserById(principalId);
        request.setUser(user);
        final Reservation reservation = reservationService.createReservation(request);
        return ResponseEntity.ok(IdWrapper.of(reservation.getId()));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> updateReservation(@PathVariable("id") UUID id,
                                                  @Valid @RequestBody UpdateReservationRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}/cancel")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
