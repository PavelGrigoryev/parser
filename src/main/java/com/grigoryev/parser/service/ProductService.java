package com.grigoryev.parser.service;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;

import java.util.List;

public interface ProductService {

    Product save(ProductDto productDto);

    void saveAll(List<ProductDto> productDtoList);

    Product update(Long id, ProductDto productDto);

    List<ProductDto> findAll(int page, int size, String sort);

    ProductDto findById(Long id);

    List<ProductDto> findByName(String name);

    List<ProductDto> findByNameStartsWith(String name);

    List<ProductDto> findByManufacturer(String manufacturer);

    void deleteById(Long id);

    void removeAll();
}
