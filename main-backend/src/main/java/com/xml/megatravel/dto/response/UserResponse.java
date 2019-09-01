package com.xml.megatravel.dto.response;


import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.model.enumeration.Role;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Builder
@Getter
public class UserResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Category category;

    private String email;

    private Boolean isActive;

    private String profilePictureUrl;
}
