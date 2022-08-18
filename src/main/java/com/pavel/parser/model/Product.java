package com.pavel.parser.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "amount")
    private String amount;

    @Column(name = "price_BYN")
    private String priceBYN;

    @Column(name = "time")
    private LocalDateTime localDateTime = LocalDateTime.now();
}
