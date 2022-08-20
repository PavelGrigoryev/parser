package com.grigoryev.parser.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String manufacturer;

    private String amount;

    private String priceBYN;

    private LocalDateTime localDateTime = LocalDateTime.now();
}
