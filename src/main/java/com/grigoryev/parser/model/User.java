package com.grigoryev.parser.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
