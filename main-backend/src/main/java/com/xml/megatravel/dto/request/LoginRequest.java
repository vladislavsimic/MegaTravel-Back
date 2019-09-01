package com.xml.megatravel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.xml.megatravel.util.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.xml.megatravel.util.ValidationConstants.PASSWORD_MIN_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String usernameOrEmail;

    @NotNull
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    private String password;

}
