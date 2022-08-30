package com.grigoryev.parser.repository;

import com.grigoryev.parser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserName(String username);
}
