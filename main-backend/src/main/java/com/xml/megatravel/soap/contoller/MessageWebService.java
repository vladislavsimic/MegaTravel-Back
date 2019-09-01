package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.model.Message;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.service.MessageService;
import com.xml.megatravel.service.ReservationService;
import com.xml.megatravel.soap.model.message.CreateMessageRequest;
import com.xml.megatravel.soap.model.message.CreateMessageResponse;
import com.xml.megatravel.soap.model.message.GetMessagesRequest;
import com.xml.megatravel.soap.model.message.GetMessagesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.soap.converter.XmlMessageConverter.*;

@Endpoint
public class MessageWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/message";

    private final MessageService messageService;

    private final ReservationService reservationService;

    public MessageWebService(MessageService messageService, ReservationService reservationService) {
        this.messageService = messageService;
        this.reservationService = reservationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getMessages(@RequestPayload GetMessagesRequest request) {
        final List<Message> messages = messageService.getMessages();
        return toGetMessagesResponseFromMessagesList(messages);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createMessageRequest")
    @ResponsePayload
    public CreateMessageResponse createMessage(@RequestPayload CreateMessageRequest request) {
        final Reservation reservation = reservationService.getReservationById(UUID.fromString(request.getReservationId()));
        final Message message = messageService.createMessage(toCreateMessageRequest(request, reservation));

        return toCreateMessageResponse(message, HttpStatus.OK);
    }
}
