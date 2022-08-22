package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.exception.NoSuchProductException;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.repository.ProductRepository;
import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.utils.MappingProductUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final MappingProductUtils mappingProductUtils;

    @Override
    public Product save(ProductDto productDto) {
        Product product = mappingProductUtils.mapToProductEntity(productDto);
        log.info("Saving new product \"{}\" with the price \"{}\"", product.getName(), product.getPriceBYN());
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<ProductDto> productDtoList) {
        log.info("Saving all products ...");
        return productRepository.saveAll(productDtoList.stream()
                .map(mappingProductUtils::mapToProductEntity)
                .toList());
    }

    @Override
    public List<ProductDto> findAll() {
        log.info("Finding all products ...");
        return productRepository.findAll().stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchProductException("No such products with ID " + id + " in database"));
        log.info("The product \"{}\" is found", product.getName());
        return mappingProductUtils.mapToProductDto(productRepository.findById(id).orElse(new Product()));
    }

    @Override
    public List<ProductDto> findByName(String name) {
        log.info("Finding products by name - \"{}\" ...", name);
        return productRepository.findByName(name).stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByNameStartsWith(String name) {
        log.info("Finding products by name starting with \"{}\" ...", name);
        return productRepository.findByNameStartsWith(name).stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByManufacturer(String manufacturer) {
        log.info("Finding products by manufacturer \"{}\" ...", manufacturer);
        return productRepository.findByManufacturer(manufacturer).stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchProductException("No such products with ID " + id + " in database"));
        log.info("The product \"{}\" has been deleted", product.getName());
        productRepository.deleteById(id);
    }
}
