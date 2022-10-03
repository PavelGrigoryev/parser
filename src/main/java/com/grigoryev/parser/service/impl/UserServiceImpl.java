package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.exception.UserWithThisNickNameIsAlreadyExistsException;
import com.grigoryev.parser.model.User;
import com.grigoryev.parser.repository.UserRepository;
import com.grigoryev.parser.security.jwt.JwtTokenUtil;
import com.grigoryev.parser.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtUserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", authentication.getName());
        log.info("Username is {}", authentication.getName());
        return userMap;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public Map<String, Object> update(String newFirstName, String newLastName, String newUserName, String newEmail, String newPassword) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User registeredUser = getRegisteredUser(newFirstName, newLastName, newUserName, newEmail, newPassword, authentication);
        User existingUser = userRepository.findUserByUserName(newUserName);
        if (existingUser != null && existingUser.getUserName().equals(newUserName)) {
            throw new UserWithThisNickNameIsAlreadyExistsException("User with nick name " + newUserName + " is already exists!");
        }
        UserDetails userDetails = userDetailsService.createUserDetails(newUserName, registeredUser.getPassword());
        String token = jwtTokenUtil.generateToken(userDetails);
        try {
            userRepository.save(registeredUser);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            responseMap.put("Email is occupied", "Another user is already registered to this email!");
            return responseMap;
        }
        log.info("New Username is {}", registeredUser.getUserName());
        log.info("Account updated successfully");
        log.info("New token is \"{}\"", token);
        log.info("The token is valid until the date : {}", jwtTokenUtil.getExpirationDateFromToken(token).toString());
        responseMap.put("newUsername", registeredUser.getUserName());
        responseMap.put("message", "Account updated successfully");
        responseMap.put("newToken", token);
        responseMap.put("The token is valid until the date : ", jwtTokenUtil.getExpirationDateFromToken(token).toString());
        return responseMap;
    }

    @Override
    public void delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User registeredUser = userRepository.findUserByUserName(authentication.getName());
        log.info("User {} was removed", registeredUser.getUserName());
        userRepository.delete(registeredUser);
    }

    private User getRegisteredUser(String newFirstName, String newLastName, String newUserName, String newEmail, String newPassword, Authentication authentication) {
        User registeredUser = userRepository.findUserByUserName(authentication.getName());
        registeredUser.setFirstName(newFirstName);
        registeredUser.setLastName(newLastName);
        registeredUser.setUserName(newUserName);
        registeredUser.setEmail(newEmail);
        registeredUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        registeredUser.setLocalDateTime(LocalDateTime.now());
        return registeredUser;
    }
}
