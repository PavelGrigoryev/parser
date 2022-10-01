package com.grigoryev.parser.service;

import com.grigoryev.parser.model.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> getUserName();

    void save(User user);

    User findUserByUserName(String username);

    Map<String, Object> update(String newFirstName, String newLastName, String newUserName, String newEmail, String newPassword);

    void delete();
}
