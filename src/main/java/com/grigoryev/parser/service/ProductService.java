package com.grigoryev.parser.service;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;

import java.util.List;

public interface ProductService {

    Product save(ProductDto productDto);

    Boolean isExist(String productName);

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    List<ProductDto> findByNameStartsWith(String name);

    List<ProductDto> findByManufacturer(String manufacturer);

    void deleteById(Long id);
}
