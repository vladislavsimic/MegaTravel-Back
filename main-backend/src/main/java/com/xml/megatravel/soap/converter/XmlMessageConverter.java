package com.xml.megatravel.soap.converter;

import com.xml.megatravel.dto.request.CreateMessageRequest;
import com.xml.megatravel.model.Message;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.enumeration.MessageType;
import com.xml.megatravel.soap.model.message.CreateMessageResponse;
import com.xml.megatravel.soap.model.message.GetMessagesResponse;
import com.xml.megatravel.soap.model.message.XmlMessage;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.xml.megatravel.soap.converter.XmlReservationConverter.toXmlReservationFromReservation;

public class XmlMessageConverter {

    public static CreateMessageRequest toCreateMessageRequest(com.xml.megatravel.soap.model.message.CreateMessageRequest request, Reservation reservation) {
        return CreateMessageRequest.builder()
                .content(request.getContent())
                .sentToEntity(MessageType.valueOf(request.getSentToEntity()))
                .reservation(reservation)
                .build();
    }

    public static CreateMessageResponse toCreateMessageResponse(Message message, HttpStatus status) {
        return CreateMessageResponse.builder()
                .id(message.getId().toString())
                .status(status.value())
                .build();
    }

    public static GetMessagesResponse toGetMessagesResponseFromMessagesList(List<Message> messages) {
        return GetMessagesResponse.builder()
                .messages(messages.stream().map(XmlMessageConverter::toXmlMessageFromMessage).collect(Collectors.toList()))
                .build();
    }

    public static XmlMessage toXmlMessageFromMessage(Message message) {
        final XmlMessage xmlMessage = XmlMessage.builder()
                .content(message.getContent())
                .reservation(toXmlReservationFromReservation(message.getReservation()))
                .sendToEntity(message.getSentToEntity().toString())
                .build();

        xmlMessage.setId(message.getId().toString());

        return xmlMessage;
    }
}
