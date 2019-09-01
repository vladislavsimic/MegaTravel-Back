package com.xml.megatravel.converter;

import com.xml.megatravel.dto.request.CreateAgentRequest;
import com.xml.megatravel.dto.request.CreateUserRequest;
import com.xml.megatravel.dto.request.RegisterUserRequest;
import com.xml.megatravel.dto.response.UserResponse;
import com.xml.megatravel.model.Address;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.Category;
import com.xml.megatravel.model.enumeration.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toUserFromRegisterUserRequest(RegisterUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .category(Category.NONE)
                .role(Role.USER)
                .isActive(true)
                .build();
    }

    public static User toUserFromCreateUserRequest(CreateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(request.getRole())
                .category(Category.NONE)
                .isActive(true)
                .build();
    }

    public static User toUserFromCreateAgentRequest(CreateAgentRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(Role.AGENT)
                .category(Category.NONE)
                .isActive(true)
                .build();
    }

    public static Agent toAgentFromCreateAgentRequest(CreateAgentRequest request, User user, Address address) {
        return Agent.builder()
                .pib(request.getPib())
                .user(user)
                .address(address)
                .build();
    }

    public static List<UserResponse> toUserResponseListFromUserList(List<User> users) {
        return users.stream().map(UserConverter::toUserResponseFromUser).collect(Collectors.toList());
    }

    public static UserResponse toUserResponseFromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .category(user.getCategory())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .role(user.getRole())
                .username(user.getUsername())
                .profilePictureUrl(user.getProfilePicture() != null ? user.getProfilePicture().getUrl() : null)
                .build();
    }

}
