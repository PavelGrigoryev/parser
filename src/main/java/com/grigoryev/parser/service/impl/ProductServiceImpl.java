package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.exception.NoSuchProductException;
import com.grigoryev.parser.model.ProductEntity;
import com.grigoryev.parser.repository.ProductRepository;
import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.utils.MappingProductUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MappingProductUtils mappingProductUtils;

    @Override
    public void save(ProductEntity productEntity) {
        log.info("Сохраняем новый продукт \"{}\" с ценой \"{}\"", productEntity.getName(), productEntity.getPriceBYN());
        productRepository.save(productEntity);
    }

    @Override
    public Boolean isExist(String name) {
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity productEntity : productEntities) {
            if (productEntity.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ProductEntity> findAll() {
        log.info("Получаем все продукты ...");
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта с ID " + id + " в базе данных");
        }
        log.info("Продукт \"{}\" найден", product.get().getName());
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findByNameStartsWith(String name) {
        log.info("Получаем продукты по имени начинающемуся с \"{}\" ...", name);
        return productRepository.findByNameStartsWith(name);
    }

    @Override
    public List<ProductEntity> findByManufacturer(String manufacturer) {
        log.info("Получаем продукты по производителю \"{}\" ...", manufacturer);
        return productRepository.findByManufacturer(manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта с ID " + id + " в базе данных");
        }
        log.info("Продукт \"{}\" удалён", product.get().getName());
        productRepository.deleteById(id);
    }
}
