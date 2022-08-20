package com.grigoryev.parser.controller;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return new ResponseEntity<>("Товар с именем \"" + productDto.getName() +
                "\" сохранён в базу данных", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<List<ProductDto>> findByNameStartsWith(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByNameStartsWith(name), HttpStatus.OK);
    }

    @GetMapping("/findByManufacturer/{manufacturer}")
    public ResponseEntity<List<ProductDto>> findByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        return new ResponseEntity<>(productService.findByManufacturer(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>("Продукт с ID " + id + " был удалён", HttpStatus.OK);
    }
}
