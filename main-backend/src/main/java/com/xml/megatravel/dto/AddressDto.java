package com.xml.megatravel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import static com.xml.megatravel.util.ValidationConstants.*;
import static com.xml.megatravel.util.ValidationConstants.ZIP_CODE_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotBlank
    @Size(max = STREET_NAME_SIZE)
    private String street;

    @NotBlank
    @Size(max = CITY_NAME_SIZE)
    private String city;

    @NotBlank
    @Size(max = COUNTRY_NAME_SIZE)
    private String country;

    @NotNull
    @DecimalMin(value = LONGITUDE_MIN)
    @DecimalMax(value = LONGITUDE_MAX)
    private Double longitude;

    @NotNull
    @DecimalMin(value = LATITUDE_MIN)
    @DecimalMax(value = LATITUDE_MAX)
    private Double latitude;

}
