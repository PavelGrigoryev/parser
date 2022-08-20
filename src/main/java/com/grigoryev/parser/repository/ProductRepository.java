package com.grigoryev.parser.repository;

import com.grigoryev.parser.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameStartsWith(String name);

    List<ProductEntity> findByManufacturer(String manufacturer);

}
