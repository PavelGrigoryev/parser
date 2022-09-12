package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.repository.ProductRepository;
import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.utils.MappingProductUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private static final long PRODUCT_ID = 1L;

    private static final String PRODUCT_NAME = "Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)";

    private static final String PRODUCT_AMOUNT = "комп.";

    private static final String PRODUCT_MANUFACTURER = "AKS";

    private static final Double PRODUCT_PRICE_BYN = 6.39;

    private ProductServiceImpl productServiceImpl;

    private ProductRepository productRepository;

    private MappingProductUtils mappingProductUtils;


    @BeforeEach
    void init() {
        productRepository = mock(ProductRepository.class);
        productServiceImpl = spy(new ProductServiceImpl(productRepository, mappingProductUtils));
    }

    @Test
    @DisplayName("check findAll method")
    void findAll() {
        Product product = getMockedProduct();
        doReturn(List.of(product)).when(productRepository).findAll();
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    private Product getMockedProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setAmount(PRODUCT_AMOUNT);
        product.setManufacturer(PRODUCT_MANUFACTURER);
        product.setPriceBYN(PRODUCT_PRICE_BYN);
        return product;
    }
}