package com.xml.megatravel.service;

import com.xml.megatravel.config.security.JwtTokenUtil;
import com.xml.megatravel.dto.JwtAuthDto;
import com.xml.megatravel.dto.request.CreateAgentRequest;
import com.xml.megatravel.dto.request.CreateUserRequest;
import com.xml.megatravel.dto.request.LoginRequest;
import com.xml.megatravel.dto.request.RegisterUserRequest;
import com.xml.megatravel.exception.ForbiddenOperationException;
import com.xml.megatravel.exception.UserAlreadyExistsException;
import com.xml.megatravel.exception.UserNotFoundException;
import com.xml.megatravel.model.Address;
import com.xml.megatravel.model.Agent;
import com.xml.megatravel.model.User;
import com.xml.megatravel.model.enumeration.Role;
import com.xml.megatravel.repository.AgentRepository;
import com.xml.megatravel.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.xml.megatravel.converter.AddressConverter.toAddressFromAddressDto;
import static com.xml.megatravel.converter.UserConverter.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AgentRepository agentRepository;

    private final AddressService addressService;

    private final JwtTokenUtil tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AgentRepository agentRepository, AddressService addressService, JwtTokenUtil tokenProvider, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.addressService = addressService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public JwtAuthDto login(LoginRequest request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail())
                .orElseThrow(() -> new UserNotFoundException("No user with given username or email found!"));
        final String jwt = tokenProvider.generateAuthToken(authentication);
        return new JwtAuthDto(jwt, user.getRole().toString());
    }

    @Transactional(rollbackFor = Exception.class)
    public User registerUser(RegisterUserRequest request) {
        checkIfUserExists(request.getUsername(), request.getEmail());

        final User user = toUserFromRegisterUserRequest(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUser(CreateUserRequest request) {
        checkIfUserExists(request.getUsername(), request.getEmail());
        if(request.getRole() == Role.AGENT) {
            throw new ForbiddenOperationException("Can't create user with role AGENT this way.");
        }

        final User user = toUserFromCreateUserRequest(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public Agent createAgent(CreateAgentRequest request) {
        checkIfUserExists(request.getUsername(), request.getEmail());

        final User user = toUserFromCreateAgentRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        final Address address = addressService.createAddress(toAddressFromAddressDto(request.getAddress()));

        final Agent agent = toAgentFromCreateAgentRequest(request, user, address);

        agentRepository.save(agent);

        return agent;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAllByRole(Role.USER);
    }

    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return getUserFromRepository(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void blockUser(UUID id) {
        final User user = getUserFromRepository(id);

        user.setIsActive(false);

        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void unblockUser(UUID id) {
        final User user = getUserFromRepository(id);

        user.setIsActive(true);

        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        final User user = getUserFromRepository(id);

        user.setIsDeleted(true);

        userRepository.save(user);
    }

    private void checkIfUserExists(String username, String email) {
        if(userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        if(userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already exists");
        }
    }

    private User getUserFromRepository(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with given id not found."));
    }

    @Transactional(readOnly = true)
    public List<User> getAgents() {
        return userRepository.findAllByRole(Role.AGENT);
    }
}
