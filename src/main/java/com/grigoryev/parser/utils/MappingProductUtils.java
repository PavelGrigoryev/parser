package com.grigoryev.parser.utils;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;
import org.springframework.stereotype.Service;

@Service
public class MappingProductUtils {

    public ProductDto mapToProductDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setManufacturer(product.getManufacturer());
        dto.setAmount(product.getAmount());
        dto.setPriceBYN(product.getPriceBYN());
        return dto;
    }

    public Product mapToProductEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setManufacturer(dto.getManufacturer());
        entity.setAmount(dto.getAmount());
        entity.setPriceBYN(dto.getPriceBYN());
        return entity;
    }
}
