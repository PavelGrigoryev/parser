package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.exception.UserWithThisNickNameIsAlreadyExistsException;
import com.grigoryev.parser.model.User;
import com.grigoryev.parser.security.jwt.JwtTokenUtil;
import com.grigoryev.parser.service.AuthenticationService;
import com.grigoryev.parser.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        String message = "message";
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                log.info("{} logged In", username);
                log.info("token is \"{}\"", token);
                log.info("The token is valid until the date : {}", jwtTokenUtil.getExpirationDateFromToken(token).toString());
                responseMap.put(message, "Logged In : " + username);
                responseMap.put("token", token);
                responseMap.put("The token is valid until the date : ", jwtTokenUtil.getExpirationDateFromToken(token).toString());
            } else {
                responseMap.put(message, "Invalid Credentials");
            }
            return responseMap;
        } catch (DisabledException e) {
            log.error(e.getMessage());
            responseMap.put(message, "User is disabled");
            return responseMap;
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            responseMap.put(message, "Invalid Credentials");
            return responseMap;
        } catch (InternalAuthenticationServiceException e) {
            log.error(e.getMessage());
            responseMap.put(message, "User is null! Register first");
            return responseMap;
        } catch (Exception e) {
            log.error(e.getMessage());
            responseMap.put(message, "Something went wrong");
            return responseMap;
        }
    }

    @Override
    public Map<String, Object> register(String firstName, String lastName, String userName, String email, String password) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        User user = getUser(firstName, lastName, userName, email, password);
        User existingUser = userService.findUserByUserName(userName);
        if (existingUser != null && existingUser.getUserName().equals(userName)) {
            throw new UserWithThisNickNameIsAlreadyExistsException("User with nick name " + userName + " is already exists!");
        }
        UserDetails userDetails = userDetailsService.createUserDetails(userName, user.getPassword());
        String token = jwtTokenUtil.generateToken(userDetails);
        try {
            userService.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            responseMap.put("Email is occupied", "Another user is already registered to this email!");
            return responseMap;
        }
        log.info("Username is {}", user.getUserName());
        log.info("Account created successfully");
        log.info("token is \"{}\"", token);
        log.info("The token is valid until the date : {}", jwtTokenUtil.getExpirationDateFromToken(token).toString());
        responseMap.put("username", user.getUserName());
        responseMap.put("message", "Account created successfully");
        responseMap.put("token", token);
        responseMap.put("The token is valid until the date : ", jwtTokenUtil.getExpirationDateFromToken(token).toString());
        return responseMap;
    }

    private static User getUser(String firstName, String lastName, String userName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole("USER");
        user.setUserName(userName);
        return user;
    }
}
