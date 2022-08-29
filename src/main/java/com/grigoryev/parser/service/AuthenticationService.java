package com.grigoryev.parser.service;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> login(String username, String password);

    Map<String, Object> register(String firstName, String lastName, String userName, String email, String password);

}
