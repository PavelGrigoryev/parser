package com.pavel.parser.service.impl;

import com.pavel.parser.model.Product;
import com.pavel.parser.repository.ProductRepository;
import com.pavel.parser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Boolean isExist(String name) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByNameStartsWith(String name) {
        return productRepository.findByNameStartsWith(name);
    }
}
