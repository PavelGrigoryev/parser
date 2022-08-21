package com.grigoryev.parser.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String manufacturer;

    private String amount;

    private String priceBYN;

}
