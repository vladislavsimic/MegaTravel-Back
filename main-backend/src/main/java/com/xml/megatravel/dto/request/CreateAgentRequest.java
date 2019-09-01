package com.xml.megatravel.dto.request;

import com.xml.megatravel.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgentRequest {
    @NotBlank
    @Size(max = FIRST_NAME_SIZE)
    private String firstName;

    @NotBlank
    @Size(max = LAST_NAME_SIZE)
    private String lastName;

    @NotBlank
    @Size(min = USERNAME_MIN_SIZE, max = USERNAME_MAX_SIZE)
    private String username;

    @NotBlank
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(max = AGENT_PIB_SIZE)
    private String pib;

    @NotNull
    private AddressDto address;
}
