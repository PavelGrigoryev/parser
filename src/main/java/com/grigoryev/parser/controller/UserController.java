package com.grigoryev.parser.controller;

import com.grigoryev.parser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API")
@Validated
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

    @Operation(
            summary = "Change your personal data", tags = "User", description = "Let's change your personal data",
            parameters = {
                    @Parameter(name = "new_first_name", description = "Enter new firstName here", example = "Josephine"),
                    @Parameter(name = "new_last_name", description = "Enter new lastName here", example = "Bloody"),
                    @Parameter(name = "new_user_name", description = "Enter new userName here", example = "DeathKnight"),
                    @Parameter(name = "new_email", description = "Enter new email here", example = "WotlkBDK@tanking.by"),
                    @Parameter(name = "new_password", description = "Enter new password here", example = "123abc")
            }
    )
    @PatchMapping
    public ResponseEntity<Map<String, Object>> update(@RequestParam("new_first_name") String newFirstName,
                                                      @RequestParam("new_last_name") String newLastName,
                                                      @RequestParam("new_user_name") String newUserName,
                                                      @RequestParam("new_email") String newEmail,
                                                      @RequestParam("new_password") String newPassword) {
        return new ResponseEntity<>(userService.update(newFirstName, newLastName, newUserName, newEmail, newPassword),
                HttpStatus.OK);
    }

    @Operation(summary = "Remove your data from database", tags = "User", description = "Let's remove your data")
    @DeleteMapping
    public ResponseEntity<String> delete() {
        userService.delete();
        return new ResponseEntity<>("Your data is successfully removed", HttpStatus.OK);
    }
}
