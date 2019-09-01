package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.CreateMessageRequest;
import com.xml.megatravel.dto.response.MessageResponse;
import com.xml.megatravel.model.Message;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.enumeration.MessageType;

import java.util.List;
import java.util.stream.Collectors;

public class MessageConverter {

    public static Message toMessageFromCreateMessageRequest(CreateMessageRequest request, Reservation reservation) {
        return Message.builder()
                .content(request.getContent())
                .reservation(reservation)
                .sentToEntity(request.getSentToEntity())
                .build();
    }

    public static List<MessageResponse> toMessageResponseListFromMessageList(List<Message> messages) {
        return messages.stream().map(MessageConverter::toMessageResponseFromMessage).collect(Collectors.toList());
    }

    public static MessageResponse toMessageResponseFromMessage(Message message) {
        return MessageResponse.builder()
                .content(message.getContent())
                .reservationId(message.getReservation().getId())
                .sentToEntity(message.getSentToEntity())
                .senderId(message.getSentToEntity() == MessageType.FOR_AGENT ? message.getReservation().getUser().getId() : message.getReservation().getProperty().getAgent().getId())
                .build();
    }

}
