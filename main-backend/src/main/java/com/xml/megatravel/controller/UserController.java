package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.request.CreateAgentRequest;
import com.xml.megatravel.dto.request.CreateUserRequest;
import com.xml.megatravel.dto.response.UserResponse;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.model.User;
import com.xml.megatravel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.UserConverter.toUserResponseFromUser;
import static com.xml.megatravel.converter.UserConverter.toUserResponseListFromUserList;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> getUsers() {
        final List<User> users = userService.getUsers();
        return ResponseEntity.ok(toUserResponseListFromUserList(users));
    }

    @GetMapping(value = "/agents")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAgents() {
        final List<User> agents = userService.getAgents();
        return ResponseEntity.ok(toUserResponseListFromUserList(agents));
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'AGENT', 'USER')")
    public ResponseEntity<UserResponse> getMe(@ApiIgnore @AuthenticationPrincipal UUID principalId) {
        final User user = userService.getUserById(principalId);
        return ResponseEntity.ok(toUserResponseFromUser(user));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<IdWrapper> createUser(@Valid @RequestBody CreateUserRequest request) {
        final User user = userService.createUser(request);
        return ResponseEntity.ok(IdWrapper.of(user.getId()));
    }

    @PostMapping(value = "/agent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<IdWrapper> createAgent(@Valid @RequestBody CreateAgentRequest request) {
        final Agent agent = userService.createAgent(request);
        // TODO: 2019-04-17 Check if we should return agent id or user id
        return ResponseEntity.ok(IdWrapper.of(agent.getUser().getId()));
    }

    @PatchMapping(value = "/{id}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> blockUser(@PathVariable("id") UUID id) {
        userService.blockUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> unblockUser(@PathVariable("id") UUID id) {
        userService.unblockUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
