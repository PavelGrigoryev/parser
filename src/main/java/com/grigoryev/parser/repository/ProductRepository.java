package com.grigoryev.parser.repository;

import com.grigoryev.parser.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameStartsWith(String name);

    List<Product> findByManufacturer(String manufacturer);

}
