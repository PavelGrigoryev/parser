package com.grigoryev.parser.repository;

import com.grigoryev.parser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{userName: '?0'}")
    User findUserByUserName(String username);
}
