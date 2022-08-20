package com.grigoryev.parser.service;

import com.grigoryev.parser.model.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductEntity productEntity);

    Boolean isExist(String productName);

    List<ProductEntity> findAll();

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByNameStartsWith(String name);

    List<ProductEntity> findByManufacturer(String manufacturer);

    void deleteById(Long id);
}
