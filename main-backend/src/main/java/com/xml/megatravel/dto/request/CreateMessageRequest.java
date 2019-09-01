package com.xml.megatravel.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.megatravel.model.Reservation;
import com.xml.megatravel.model.enumeration.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.MESSAGE_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequest {

    @NotNull
    @Size(max = MESSAGE_SIZE)
    private String content;

    @NotNull
    private MessageType sentToEntity;

    @JsonIgnore
    private Reservation reservation;
}
