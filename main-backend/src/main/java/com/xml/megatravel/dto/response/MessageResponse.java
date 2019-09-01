package com.xml.megatravel.dto.response;

import com.xml.megatravel.model.enumeration.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class MessageResponse {

    private MessageType sentToEntity;

    private String content;

    private UUID senderId;

    private UUID reservationId;
}
