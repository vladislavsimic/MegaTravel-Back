package com.xml.megatravel.soap.contoller;

import com.xml.megatravel.dto.JwtAuthDto;
import com.xml.megatravel.dto.request.LoginRequest;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.service.AgentService;
import com.xml.megatravel.service.UserService;
import com.xml.megatravel.soap.model.agent.GetAgentsRequest;
import com.xml.megatravel.soap.model.agent.GetAgentsResponse;
import com.xml.megatravel.soap.model.agent.LoginAgentRequest;
import com.xml.megatravel.soap.model.agent.LoginAgentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static com.xml.megatravel.soap.converter.XmlAgentConverter.toGetAgentsResponseFromAgentsList;
import static com.xml.megatravel.soap.converter.XmlAgentConverter.toLoginAgentResponseFromAgent;

@Endpoint
public class AgentWebService {
    private static final String NAMESPACE_URI = "http://www.xml.com/megatravel/soap/model/agent";

    private final AgentService agentService;

    private final UserService userService;

    public AgentWebService(AgentService agentService, UserService userService) {
        this.agentService = agentService;
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginAgentRequest")
    @ResponsePayload
    public LoginAgentResponse loginAgent(@RequestPayload LoginAgentRequest request) {
        try {
            final JwtAuthDto authDto = userService.login(
                    LoginRequest.builder()
                            .usernameOrEmail(request.getEmailOrUsername())
                            .password(request.getPassword())
                            .build()
            );
        } catch (Exception e) {
            return toLoginAgentResponseFromAgent(null, HttpStatus.UNAUTHORIZED.value());
        }

        final Agent agent = request.getEmailOrUsername() != null ? agentService.getAgentByUsernameOrEmail(request.getEmailOrUsername()) : null;
        final int status = agent != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();

        return toLoginAgentResponseFromAgent(agent, status);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAgentsRequest")
    @ResponsePayload
    public GetAgentsResponse getAgents(@RequestPayload GetAgentsRequest request) {
        final List<Agent> agents = agentService.getAgents();
        return toGetAgentsResponseFromAgentsList(agents);
    }
}
