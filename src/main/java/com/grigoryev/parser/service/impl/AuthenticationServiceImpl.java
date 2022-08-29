package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.model.User;
import com.grigoryev.parser.repository.UserRepository;
import com.grigoryev.parser.security.jwt.JwtTokenUtil;
import com.grigoryev.parser.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> responseMap = new HashMap<>();
        String message = "message";
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                log.info("{} logged In", username);
                log.info("token is \"{}\"", token);
                responseMap.put(message, "Logged In : " + username);
                responseMap.put("token", token);
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
        } catch (Exception e) {
            log.error(e.getMessage());
            responseMap.put(message, "Something went wrong");
            return responseMap;
        }
    }

    @Override
    public Map<String, Object> register(String firstName, String lastName, String userName, String email, String password) {
        Map<String, Object> responseMap = new HashMap<>();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole("USER");
        user.setUserName(userName);
        UserDetails userDetails = userDetailsService.createUserDetails(userName, user.getPassword());
        String token = jwtTokenUtil.generateToken(userDetails);
        userRepository.save(user);
        log.info("Username is {}", userName);
        log.info("Account created successfully");
        log.info("token is \"{}\"", token);
        responseMap.put("username", userName);
        responseMap.put("message", "Account created successfully");
        responseMap.put("token", token);
        return responseMap;
    }
}
