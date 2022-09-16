package com.grigoryev.parser.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("products")
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @Indexed(unique = true)
    private String name;

    private String manufacturer;

    private String amount;

    private String priceBYN;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
