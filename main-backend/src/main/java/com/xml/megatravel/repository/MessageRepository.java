package com.xml.megatravel.repository;

import com.xml.megatravel.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findAllByReservationUserId(UUID userId);

    List<Message> findAllByReservationPropertyAgentId(UUID userId);
}
