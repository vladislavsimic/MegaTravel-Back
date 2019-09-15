package com.xml.megatravel.repository;

import com.xml.megatravel.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    Boolean existsByTypeId(UUID typeId);

//    @Query("SELECT DISTINCT p " +
//            "FROM Property p " +
//            "LEFT JOIN Address a ON p.address.id = a.id " +
//            "LEFT JOIN Type t on p.type.id = t.id " +
//            "LEFT JOIN PropertyService ps on ps.property.id = p.id " +
//            "LEFT JOIN Reservation r on r.property.id = p.id " +
//            "WHERE a.city = ?1 AND p.numberOfPeople >= ?4 AND NOT (?3 >= nullif(r.startDate, ?3)  AND ?2 <= nullif(r.endDate, ?2)) ")
//    List<Property> findAllBySearchCriteria(String city, LocalDateTime startDate, LocalDateTime endDate, Integer numberOfPeople, UUID typeId, Integer stars, Double distance, List<UUID> serviceIds, Boolean freeCancel, Integer daysToCancel);

    List<Property> findAllByAgentId(UUID agentId);
}
