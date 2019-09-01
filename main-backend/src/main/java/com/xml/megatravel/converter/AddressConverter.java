package com.xml.megatravel.converter;

import com.xml.megatravel.dto.AddressDto;
import com.xml.megatravel.dto.request.CreateAgentRequest;
import com.xml.megatravel.model.Address;

public class AddressConverter {

    public static Address toAddressFromAddressDto(AddressDto request) {
        return Address.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .street(request.getStreet())
                .build();
    }

    public static AddressDto toAddressDtoFromAddress(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .street(address.getStreet())
                .build();
    }
}
