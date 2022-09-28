package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.exception.NoSuchProductException;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.repository.ProductRepository;
import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Product save(ProductDto productDto) {
        Product product = productMapper.mapToProductEntity(productDto);
        log.info("Saving new product \"{}\" with the price \"{}\"", product.getName(), product.getPriceBYN());
        return productRepository.save(product);
    }

    @Override
    public void saveAll(List<ProductDto> productDtoList) {
        log.info("Saving {} products ...", productDtoList.size());
        productRepository.saveAll(productDtoList.stream()
                .map(productMapper::mapToProductEntity)
                .toList());
    }

    @Override
    public List<ProductDto> findAll(int page, int size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Product> pagedResult = productRepository.findAll(paging);
        log.info("Finding {} products ...", pagedResult.getSize());
        return pagedResult.stream()
                .map(productMapper::mapToProductDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchProductException("No such products with ID " + id + " in database"));
        log.info("The product \"{}\" is found", product.getName());
        return productMapper.mapToProductDto(productRepository.findById(id).orElse(new Product()));
    }

    @Override
    public List<ProductDto> findByName(String name) {
        log.info("Finding products by name - \"{}\" ...", name);
        return productRepository.findByName(name).stream()
                .map(productMapper::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByNameStartsWith(String name) {
        List<Product> productList = productRepository.findByNameStartsWith(name);
        log.info("Finding {} products by name starting with \"{}\" ...", productList.size(), name);
        return productList.stream()
                .map(productMapper::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByManufacturer(String manufacturer) {
        List<Product> productList = productRepository.findByManufacturer(manufacturer);
        log.info("Finding {} products by manufacturer \"{}\" ...", productList.size(), manufacturer);
        return productList.stream()
                .map(productMapper::mapToProductDto)
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
