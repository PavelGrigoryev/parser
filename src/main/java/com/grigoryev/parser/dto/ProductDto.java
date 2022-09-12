package com.grigoryev.parser.dto;

import lombok.Data;

@Data
public class ProductDto {

    private long id;

    private String name;

    private String manufacturer;

    private String amount;

    private Double priceBYN;

}
