package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.CreateReservationRequest;
import com.xml.megatravel.dto.request.UpdateReservationRequest;
import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.enumeration.ReservationStatus;
import com.xml.megatravel.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.ReservationConverter.toReservationFromCreateReservationRequest;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with given Id not found."));
    }

    @Transactional(rollbackFor = Exception.class)
    public Reservation createReservation(CreateReservationRequest request) {
        final Reservation reservation = toReservationFromCreateReservationRequest(request);

        // TODO: 2019-06-25 check if reservation can be made

        reservationRepository.save(reservation);

        return reservation;
    }

    @Transactional(rollbackFor = Exception.class)
    public Reservation updateReservation(Reservation reservation, UpdateReservationRequest request) {
        // TODO: 2019-06-25 implement
        return reservation;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteReservation(UUID id) {
        final Reservation reservation = getReservationById(id);

        reservation.setIsDeleted(true);
        reservation.setReservationStatus(ReservationStatus.CANCELED);

        reservationRepository.save(reservation);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean updateReservationStatus(Reservation reservation, String status) {
        if(reservation.getReservationStatus() == ReservationStatus.SUCCESSFUL || reservation.getReservationStatus() == ReservationStatus.CANCELED) {
            return false;
        }
        reservation.setReservationStatus(ReservationStatus.valueOf(status));

        reservationRepository.save(reservation);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByUserId(UUID principalId) {
        return reservationRepository.findAllByUserId(principalId);
    }

    @Transactional(readOnly = true)
    public Reservation getReservationByUserId(UUID reservationId, UUID principalId) {
        return reservationRepository.findByIdAndUserId(reservationId, principalId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with given Id not found for this user."));
    }
}
