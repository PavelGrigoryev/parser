package com.pavel.parser.service;

import com.pavel.parser.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    Boolean isExist(String productName);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByNameStartsWith(String name);

    List<Product> findByManufacturer(String manufacturer);

    void deleteById(Long id);
}
