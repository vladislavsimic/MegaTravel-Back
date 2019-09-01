package com.xml.megatravel.repository;

import com.xml.megatravel.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeRepository extends JpaRepository<Type, UUID> {

    Boolean existsByName(String name);
}
