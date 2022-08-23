package com.grigoryev.parser.service;

import com.grigoryev.parser.model.RoleEntity;
import com.grigoryev.parser.model.UserEntity;
import com.grigoryev.parser.repository.RoleEntityRepository;
import com.grigoryev.parser.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserEntityRepository userEntityRepository;

    private final RoleEntityRepository roleEntityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntity save(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        log.warn(passwordEncoder.encode("password"));
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }
}
