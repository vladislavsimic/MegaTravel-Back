package com.xml.megatravel.service;

import com.xml.megatravel.exception.EntityNotFoundException;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.repository.AgentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Transactional(readOnly = true)
    public Agent getAgentById(UUID id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent with given Id not found."));
    }

    @Transactional(readOnly = true)
    public Agent getAgentByUserId(UUID id) {
        return agentRepository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent with given Id not found."));
    }

    @Transactional(readOnly = true)
    public Agent getAgentByUsernameOrEmail(String usernameOrEmail) {
        return agentRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new EntityNotFoundException("Agent with given username or email not found."));
    }

    @Transactional(readOnly = true)
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }
}
