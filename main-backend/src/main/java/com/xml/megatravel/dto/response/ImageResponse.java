package com.xml.megatravel.dto.response;

import com.xml.megatravel.model.enumeration.ImageType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ImageResponse {

    private UUID id;

    private String url;

    private ImageType entityType;

    private UUID entityId;
}
