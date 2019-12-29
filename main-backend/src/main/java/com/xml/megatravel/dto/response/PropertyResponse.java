package com.xml.megatravel.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xml.megatravel.dto.AddressDto;
import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.util.DateTimeUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@Setter
public class PropertyResponse {

    private UUID id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer stars;

    private Integer numberOfPeople;

    private Integer numberOfCancellationDays;

    private Double price;

    private UUID type;

    private AddressDto address;

    private UUID agentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;

    private List<String> propertyServices;

    private List<String> imageUrls;

}
