package com.xml.megatravel.repository;

import com.xml.megatravel.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent, UUID> {

    @Query("SELECT a FROM Agent a " +
            "JOIN User u on a.user.id = u.id " +
            "JOIN Address ad ON a.address.id = ad.id " +
            "WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail ")
    Optional<Agent> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    Optional<Agent> findByUserId(UUID userId);
}
