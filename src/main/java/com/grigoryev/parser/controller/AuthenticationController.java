package com.grigoryev.parser.controller;

import com.grigoryev.parser.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "The Authentication API")
@Validated
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Login and get jwt token", tags = "Authentication",
            description = "Entering login and password, we get jwt token",
            parameters = {
                    @Parameter(name = "user_name", description = "Enter userName here", example = "Undeadsanta"),
                    @Parameter(name = "password", description = "Enter password here", example = "abc123")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("user_name") String username,
                                                     @RequestParam("password") String password) {
        return new ResponseEntity<>(authenticationService.login(username, password), HttpStatus.OK);
    }

    @Operation(
            summary = "Register and get jwt token", tags = "Authentication", description = "Let's register quickly",
            parameters = {
                    @Parameter(name = "first_name", description = "Enter firstName here", example = "Pavel"),
                    @Parameter(name = "last_name", description = "Enter lastName here", example = "Grigoryev"),
                    @Parameter(name = "user_name", description = "Enter userName here", example = "Undeadsanta"),
                    @Parameter(name = "email", description = "Enter email here", example = "DeadManWalking@example.com"),
                    @Parameter(name = "password", description = "Enter password here", example = "abc123")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestParam("first_name") String firstName,
                                                        @RequestParam("last_name") String lastName,
                                                        @RequestParam("user_name") String userName,
                                                        @RequestParam("email") String email,
                                                        @RequestParam("password") String password) {
        return new ResponseEntity<>(authenticationService.register(firstName, lastName, userName, email, password),
                HttpStatus.OK);
    }
}
