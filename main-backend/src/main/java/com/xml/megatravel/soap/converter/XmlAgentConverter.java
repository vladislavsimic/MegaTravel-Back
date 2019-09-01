package com.xml.megatravel.soap.converter;

import com.xml.megatravel.model.Address;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.model.User;
import com.xml.megatravel.soap.model.address.XmlAddress;
import com.xml.megatravel.soap.model.agent.GetAgentsResponse;
import com.xml.megatravel.soap.model.agent.LoginAgentResponse;
import com.xml.megatravel.soap.model.agent.XmlAgent;
import com.xml.megatravel.soap.model.category.XmlCategory;
import com.xml.megatravel.soap.model.user.XmlUser;

import java.util.List;
import java.util.stream.Collectors;

import static com.xml.megatravel.soap.converter.XmlPropertyConverter.toXmlAddressFromAddress;

public class XmlAgentConverter {

    public static LoginAgentResponse toLoginAgentResponseFromAgent(Agent agent, Integer status) {
        if(agent == null) {
            return LoginAgentResponse.builder()
                    .status(status)
                    .build();
        }

        return LoginAgentResponse.builder()
                .agent(toXmlAgentFromAgent(agent))
                .status(status)
                .build();
    }

    public static GetAgentsResponse toGetAgentsResponseFromAgentsList(List<Agent> agents) {
        return GetAgentsResponse.builder()
                .agents(agents.stream().map(XmlAgentConverter::toXmlAgentFromAgent).collect(Collectors.toList()))
                .build();
    }

    public static XmlAgent toXmlAgentFromAgent(Agent agent) {
        final User user = agent.getUser();

        final XmlAgent xmlAgent = XmlAgent.builder()
                .user(XmlUser.builder()
                        .firstNme(user.getFirstName())
                        .lastName(user.getLastName())
                        .category(user.getCategory().name())
                        .email(user.getEmail())
                        .isActive(user.getIsActive())
                        .password(user.getPassword())
                        .role(user.getRole().name())
                        .username(user.getUsername())
                        .build())
                .address(toXmlAddressFromAddress(agent.getAddress()))
                .pib(agent.getPib())
                .build();

        xmlAgent.setId(agent.getId().toString());

        return xmlAgent;
    }
}
