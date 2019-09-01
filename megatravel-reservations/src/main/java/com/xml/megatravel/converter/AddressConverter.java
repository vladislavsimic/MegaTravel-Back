package com.xml.megatravel.converter;

import com.xml.megatravel.dto.AddressDto;
import com.xml.megatravel.model.Address;

public class AddressConverter {

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
