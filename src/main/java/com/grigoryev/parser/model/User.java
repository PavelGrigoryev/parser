package com.grigoryev.parser.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "This field must contain only letters of the Russian" +
            " and English alphabets without spaces in any case")
    private String userName;

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "This field must contain only letters of the Russian" +
            " and English alphabets without spaces in any case")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "This field must contain only letters of the Russian" +
            " and English alphabets without spaces in any case")
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private String role;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
