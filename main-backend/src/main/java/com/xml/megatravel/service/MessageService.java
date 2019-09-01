package com.xml.megatravel.service;

import com.xml.megatravel.dto.request.CreateMessageRequest;
import com.xml.megatravel.exception.ForbiddenOperationException;
import com.xml.megatravel.model.Message;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.MessageType;
import com.xml.megatravel.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.MessageConverter.toMessageFromCreateMessageRequest;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final ReservationService reservationService;

    private final UserService userService;

    public MessageService(MessageRepository messageRepository, ReservationService reservationService, UserService userService) {
        this.messageRepository = messageRepository;
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Message createMessage(CreateMessageRequest request) {
        final Message message = toMessageFromCreateMessageRequest(request, request.getReservation());

        messageRepository.save(message);

        return message;
    }

    @Transactional(rollbackFor = Exception.class)
    public Message sendMessageToAgent(CreateMessageRequest request, UUID reservationId, UUID userId) {
        final Reservation reservation = reservationService.getReservationById(reservationId);
        final User user = reservation.getUser();
        if(user != null && !reservation.getUser().getId().equals(userId)) {
            throw new ForbiddenOperationException("Reservation not found for this user!");
        }

        final Message message = toMessageFromCreateMessageRequest(request, reservation);

        message.setSentToEntity(MessageType.FOR_AGENT);

        messageRepository.save(message);

        return message;

    }

    @Transactional(readOnly = true)
    public List<Message> getByUserId(UUID principalId) {
        final User user = userService.getUserById(principalId);

        switch (user.getRole()) {
            case USER: return messageRepository.findAllByReservationUserId(principalId);
            case AGENT: return messageRepository.findAllByReservationPropertyAgentId(principalId);
            default: return Collections.emptyList();
        }

    }
}
