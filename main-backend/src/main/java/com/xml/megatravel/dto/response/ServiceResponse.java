package com.xml.megatravel.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ServiceResponse {

    private UUID id;

    private String name;
}
