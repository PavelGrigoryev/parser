package com.grigoryev.parser.controller;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Products", description = "The Product API")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Save one product", tags = "Products", description = "Let's save our product",
            requestBody = @io.swagger.v3.oas.annotations.parameters
                    .RequestBody(description = "This is our RequestBody for product example",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            )
    )
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all products", tags = "Products", description = "Let's find all the products")
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Find one product by {name}", tags = "Products", description = "Let's find our product by name",
            parameters = @Parameter(name = "name", description = "Enter name here", example = "Ручка деревянная №1, белая")
    )
    @GetMapping("{name}")
    public ResponseEntity<ProductDto> findByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all products by name starts with {...}", tags = "Products",
            description = "Let's find our products by name starts with {...}",
            parameters = @Parameter(name = "name", description = "Enter name of product here", example = "Ручка")
    )
    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<List<ProductDto>> findByNameStartsWith(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByNameStartsWith(name), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all products by {manufacturer}", tags = "Products",
            description = "Let's find our products by manufacturer",
            parameters = @Parameter(name = "manufacturer", description = "Enter manufacturer here", example = "ARNI")
    )
    @GetMapping("/findByManufacturer/{manufacturer}")
    public ResponseEntity<List<ProductDto>> findByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        return new ResponseEntity<>(productService.findByManufacturer(manufacturer), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete one product by {name}", tags = "Products", description = "Let's delete our product by name",
            parameters = @Parameter(name = "name", description = "Enter name here", example = "Ручка деревянная №1, белая")
    )
    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteByName(@PathVariable("name") String name) {
        productService.deleteByName(name);
        return new ResponseEntity<>("The product with ID " + name + " has been deleted", HttpStatus.OK);
    }
}
