package com.xml.megatravel.soap.model.agent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "emailOrUsername",
        "password"
})
@XmlRootElement(name = "loginAgentRequest")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAgentRequest {

    @XmlElement(required = true)
    protected String emailOrUsername;
    @XmlElement(required = true)
    protected String password;

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
