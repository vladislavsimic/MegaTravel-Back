package com.xml.megatravel.repository;

import com.xml.megatravel.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {

    Boolean existsByName(String name);

}
