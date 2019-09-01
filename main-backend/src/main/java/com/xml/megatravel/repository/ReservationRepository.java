package com.xml.megatravel.repository;

import com.xml.megatravel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findAllByUserId(UUID userId);

    Optional<Reservation> findByIdAndUserId(UUID reservationId, UUID userId);
}
