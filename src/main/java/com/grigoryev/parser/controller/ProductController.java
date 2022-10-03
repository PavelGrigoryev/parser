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

    @Operation(
            summary = "Update one product", tags = "Products", description = "Let's update our product",
            requestBody = @io.swagger.v3.oas.annotations.parameters
                    .RequestBody(description = "This is our RequestBody for product example",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            )
    )
    @Parameter(name = "id", description = "Enter id here", example = "2068")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.update(id, productDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all products", tags = "Products", description = "Let's find all the products with pagination",
            parameters = {
                    @Parameter(name = "page_number", description = "Enter a page number here", example = "0"),
                    @Parameter(name = "page_size", description = "Enter a page size here", example = "80"),
                    @Parameter(name = "sort_by", description = "Enter by what value you want to sort the page: id, name," +
                            " manufacturer, priceBYN", example = "id")
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(
            @RequestParam("page_number") int page,
            @RequestParam("page_size") int size,
            @RequestParam("sort_by") String sort
            ) {
        return new ResponseEntity<>(productService.findAll(page, size, sort), HttpStatus.OK);
    }

    @Operation(
            summary = "Find one product by {id}", tags = "Products", description = "Let's find our product by id",
            parameters = @Parameter(name = "id", description = "Enter id here", example = "333")
    )
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all products by name starts with {...}", tags = "Products",
            description = "Let's find our products by name starts with {...}",
            parameters = @Parameter(name = "name", description = "Enter name of product here", example = "Перфоратор")
    )
    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<List<ProductDto>> findByNameStartsWith(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findByNameStartsWith(name), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all products by {manufacturer}", tags = "Products",
            description = "Let's find our products by manufacturer",
            parameters = @Parameter(name = "manufacturer", description = "Enter manufacturer here", example = "BOSCH")
    )
    @GetMapping("/findByManufacturer/{manufacturer}")
    public ResponseEntity<List<ProductDto>> findByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        return new ResponseEntity<>(productService.findByManufacturer(manufacturer), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete one product by {id}", tags = "Products", description = "Let's delete our product by id",
            parameters = @Parameter(name = "id", description = "Enter id here", example = "2068")
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>("The product with ID " + id + " has been deleted", HttpStatus.OK);
    }

    @Operation(summary = "Remove all products from database", tags = "Products", description = "Let's remove all products")
    @DeleteMapping
    public ResponseEntity<String> removeAll() {
        productService.removeAll();
        return new ResponseEntity<>("All products removed!!!", HttpStatus.OK);
    }
}
