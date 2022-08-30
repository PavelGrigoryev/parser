package com.grigoryev.parser.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("products")
public class Product {

    @Id
    private String name;

    private String manufacturer;

    private String amount;

    private String priceBYN;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
