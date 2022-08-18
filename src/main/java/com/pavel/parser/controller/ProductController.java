package com.pavel.parser.controller;

import com.pavel.parser.model.Product;
import com.pavel.parser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    private List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/findById/{id}")
    private Optional<Product> findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping("/findByNameStartsWith/{name}")
    private List<Product> findByNameStartsWith(@PathVariable("name") String name) {
        return productService.findByNameStartsWith(name);
    }

    @GetMapping("/findByPriceBYN/{priceBYN}")
    private List<Product> findByPriceBYN(@PathVariable("priceBYN") String priceBYN) {
        return productService.findByPriceBYN(priceBYN);
    }

}
