package com.grigoryev.parser.controller;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
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
        return new ResponseEntity<>("The product with ID " + id + " has been deleted", HttpStatus.OK);
    }
}
