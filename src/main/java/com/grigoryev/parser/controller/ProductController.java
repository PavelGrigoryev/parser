package com.grigoryev.parser.controller;

import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>("Товар с именем \"" + product.getName() + "\" сохранён в базу данных под ID " +
                product.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<List<Product>> findByNameStartsWith(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByNameStartsWith(name), HttpStatus.OK);
    }

    @GetMapping("/findByManufacturer/{manufacturer}")
    public ResponseEntity<List<Product>> findByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        return new ResponseEntity<>(productService.findByManufacturer(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>("Продукт с ID " + id + " был удалён", HttpStatus.OK);
    }
}
