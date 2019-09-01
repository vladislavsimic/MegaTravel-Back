package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreateMessageRequest;
import com.xml.megatravel.dto.response.MessageResponse;
import com.xml.megatravel.model.Message;
import com.xml.megatravel.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.MessageConverter.toMessageResponseListFromMessageList;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'AGENT')")
    public ResponseEntity<List<MessageResponse>> getMessages(@ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final List<Message> messages = messageService.getByUserId(principalId);
        return ResponseEntity.ok(toMessageResponseListFromMessageList(messages));
    }

    @PostMapping(value = "/{reservationId}/agent")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<IdWrapper> sendMessageToAgent(@PathVariable("reservationId") UUID reservationId,
                                                        @Valid @RequestBody CreateMessageRequest request,
                                                        @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final Message message = messageService.sendMessageToAgent(request, reservationId, principalId);
        return ResponseEntity.ok(IdWrapper.of(message.getId()));
    }

    @PostMapping(value = "/{reservationId}/admin")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<IdWrapper> sendMessageToAdmin(@PathVariable("reservationId") UUID reservationId,
                                                        @Valid @RequestBody CreateMessageRequest request,
                                                        @ApiIgnore @AuthenticationPrincipal UUID principalId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id) {
        return ResponseEntity.noContent().build();
    }
}
