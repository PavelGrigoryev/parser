package com.grigoryev.parser.controller;

import com.grigoryev.parser.model.ProductEntity;
import com.grigoryev.parser.service.ProductService;
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
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductEntity productEntity) {
        productService.save(productEntity);
        return new ResponseEntity<>("Товар с именем \"" + productEntity.getName() + "\" сохранён в базу данных под ID " +
                productEntity.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<ProductEntity>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<List<ProductEntity>> findByNameStartsWith(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByNameStartsWith(name), HttpStatus.OK);
    }

    @GetMapping("/findByManufacturer/{manufacturer}")
    public ResponseEntity<List<ProductEntity>> findByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        return new ResponseEntity<>(productService.findByManufacturer(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>("Продукт с ID " + id + " был удалён", HttpStatus.OK);
    }
}
