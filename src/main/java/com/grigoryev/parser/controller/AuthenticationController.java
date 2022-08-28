package com.grigoryev.parser.controller;

import com.grigoryev.parser.model.User;
import com.grigoryev.parser.repository.UserRepository;
import com.grigoryev.parser.security.jwt.JwtTokenUtil;
import com.grigoryev.parser.service.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "The Authentication API")
@AllArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Operation(
            summary = "Login and get jwt token", tags = "Authentication",
            description = "Entering login and password, we get jwt token",
            parameters = {
                    @Parameter(name = "user_name", description = "Enter userName here", example = "Undeadsanta"),
                    @Parameter(name = "password", description = "Enter password here", example = "abc123")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("user_name") String username,
                                       @RequestParam("password") String password) {
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
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put(message, "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            log.error(e.getMessage());
            responseMap.put(message, "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            responseMap.put(message, "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseMap.put(message, "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @Operation(
            summary = "Register and get jwt token", tags = "Authentication", description = "Let's register quickly",
            parameters = {
                    @Parameter(name = "first_name", description = "Enter firstName here", example = "Jerry"),
                    @Parameter(name = "last_name", description = "Enter lastName here", example = "Afro"),
                    @Parameter(name = "user_name", description = "Enter userName here", example = "Jigsaw"),
                    @Parameter(name = "email", description = "Enter email here", example = "jigsaw@example.com"),
                    @Parameter(name = "password", description = "Enter password here", example = "321sec")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestParam("first_name") String firstName,
                                      @RequestParam("last_name") String lastName,
                                      @RequestParam("user_name") String userName,
                                      @RequestParam("email") String email,
                                      @RequestParam("password") String password) {
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
        return ResponseEntity.ok(responseMap);
    }
}
