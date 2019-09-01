package com.xml.megatravel.service;

import com.xml.megatravel.model.Address;
import com.xml.megatravel.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
