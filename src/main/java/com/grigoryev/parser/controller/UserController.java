package com.grigoryev.parser.controller;

import com.grigoryev.parser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get authenticated userName", tags = "User",
            description = "I wonder what is the name of the authorized user?"
    )
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserName() {
        return new ResponseEntity<>(userService.getUserName(), HttpStatus.OK);
    }
}
