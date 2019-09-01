package com.xml.megatravel.soap.model.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "agent",
        "status"
})
@XmlRootElement(name = "loginAgentResponse")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAgentResponse {

    @XmlElement(required = true)
    protected XmlAgent agent;
    @XmlElement(required = true)
    protected int status;

    public XmlAgent getAgent() {
        return agent;
    }

    public void setAgent(XmlAgent agent) {
        this.agent = agent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
