package com.pavel.parser.service.impl;

import com.pavel.parser.exception.NoSuchProductException;
import com.pavel.parser.model.Product;
import com.pavel.parser.repository.ProductRepository;
import com.pavel.parser.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        log.info("Сохраняем новый продукт \"{}\" с ценой \"{}\"", product.getName(), product.getPriceBYN());
        productRepository.save(product);
    }

    @Override
    public Boolean isExist(String name) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        log.info("Получаем все продукты");
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта с ID " + id + " в базе данных");
        }
        log.info("Продукт \"{}\" найден", product.get().getName());
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByNameStartsWith(String name) {
        log.info("Получаем продукты по имени начинающемуся с \"{}\"", name);
        return productRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        log.info("Получаем продукты по производителю \"{}\"", manufacturer);
        return productRepository.findByManufacturer(manufacturer);
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
