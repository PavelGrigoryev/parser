package com.grigoryev.parser.repository;

import com.grigoryev.parser.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findByNameStartsWith(String name);

    List<Product> findByManufacturer(String manufacturer);

}
