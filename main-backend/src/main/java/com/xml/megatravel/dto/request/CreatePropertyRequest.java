package com.xml.megatravel.dto.request;

import com.xml.megatravel.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.util.ValidationConstants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePropertyRequest {

    @NotBlank
    @Size(max = PROPERTY_NAME_SIZE)
    private String name;

    @NotBlank
    @Size(max = PROPERTY_DESCRIPTION_SIZE)
    private String description;

    @Size(max = PROPERTY_MAX_STARS)
    @PositiveOrZero
    private Integer stars;

    @NotNull
    @PositiveOrZero
    private Integer numberOfPeople;

    @NotNull
    @PositiveOrZero
    private Integer numberOfCancellationDays;

    @NotNull
    @PositiveOrZero
    private Double summerPrice;

    @NotNull
    @PositiveOrZero
    private Double winterPrice;

    @NotNull
    @PositiveOrZero
    private Double springPrice;

    @NotNull
    @PositiveOrZero
    private Double autumnPrice;

    @NotNull
    private UUID typeId;

    @NotNull
    private AddressDto address;

    @NotNull
    private UUID agentId;

    private List<UUID> services = new ArrayList<>();
}
