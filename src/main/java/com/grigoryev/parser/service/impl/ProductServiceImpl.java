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
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final MappingProductUtils mappingProductUtils;

    @Override
    public Product save(ProductDto productDto) {
        Product product = mappingProductUtils.mapToProductEntity(productDto);
        log.info("Сохраняем новый продукт \"{}\" с ценой \"{}\"", product.getName(), product.getPriceBYN());
        return productRepository.save(product);
    }

    @Override
    public Boolean isExist(String name) {
        List<Product> productEntities = productRepository.findAll();
        for (Product product : productEntities) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ProductDto> findAll() {
        log.info("Получаем все продукты ...");
        return productRepository.findAll().stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта с ID " + id + " в базе данных");
        }
        log.info("Продукт \"{}\" найден", product.get().getName());
        return mappingProductUtils.mapToProductDto(productRepository.findById(id).orElse(new Product()));
    }

    @Override
    public List<ProductDto> findByNameStartsWith(String name) {
        log.info("Получаем продукты по имени начинающемуся с \"{}\" ...", name);
        return productRepository.findByNameStartsWith(name).stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findByManufacturer(String manufacturer) {
        log.info("Получаем продукты по производителю \"{}\" ...", manufacturer);
        return productRepository.findByManufacturer(manufacturer).stream()
                .map(mappingProductUtils::mapToProductDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта с ID " + id + " в базе данных");
        }
        log.info("Продукт \"{}\" удалён", product.get().getName());
        productRepository.deleteById(id);
    }
}
