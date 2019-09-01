package com.xml.megatravel.controller;

import com.xml.megatravel.dto.IdWrapper;
import com.xml.megatravel.dto.JwtAuthDto;
import com.xml.megatravel.dto.request.LoginRequest;
import com.xml.megatravel.dto.request.RegisterUserRequest;
import com.xml.megatravel.model.User;
import com.xml.megatravel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtAuthDto> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<IdWrapper> register(@Valid @RequestBody RegisterUserRequest request) {
        final User user = userService.registerUser(request);
        return ResponseEntity.ok(IdWrapper.of(user.getId()));
    }
}
