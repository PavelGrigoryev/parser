package com.grigoryev.parser.service.impl;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.exception.NoSuchProductException;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.repository.ProductRepository;
import com.grigoryev.parser.utils.MappingProductUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private static final long PRODUCT_ID = 1L;

    private static final String PRODUCT_NAME = "Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)";

    private static final String PRODUCT_AMOUNT = "комп.";

    private static final String PRODUCT_MANUFACTURER = "AKS";

    private static final String PRODUCT_PRICE_BYN = "6.39";

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
        ProductDto productDto = getMockedProductDto();
        doReturn(List.of(productDto)).when(productRepository).findAll();
        List<ProductDto> productDtoList = productServiceImpl.findAll();
        assertEquals(1, productDtoList.size());
        assertEquals(productDto, productDtoList.get(0));
    }

    @Test
    @DisplayName("check if error throws when Product is not found")
    void findById_throws_exception() {
        doThrow(new NoSuchProductException("Product hasn't found"))
                .when(productRepository).findById(PRODUCT_ID);

        Exception exception = assertThrows(NoSuchProductException.class, () -> productServiceImpl.findById(PRODUCT_ID));

        String expectedMessage = "Product hasn't found";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("check if Product returns when it is found")
    void findById_returns_product() {
        ProductDto mockedProduct = getMockedProductDto();
        doReturn(Optional.of(mockedProduct))
                .when(productRepository).findById(PRODUCT_ID);

        ProductDto product = productServiceImpl.findById(PRODUCT_ID);

        assertEquals(mockedProduct, product);
    }

    @Test
    @DisplayName("check if Product deletes by id")
    void deleteById() {
        doNothing().when(productRepository).deleteById(PRODUCT_ID);
        productServiceImpl.deleteById(PRODUCT_ID);

        verify(productRepository, times(1)).deleteById(PRODUCT_ID);
        verify(productRepository, times(1)).deleteById(anyLong());
        verify(productRepository, never()).findById(anyLong());
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

    private ProductDto getMockedProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(PRODUCT_ID);
        productDto.setName(PRODUCT_NAME);
        productDto.setAmount(PRODUCT_AMOUNT);
        productDto.setManufacturer(PRODUCT_MANUFACTURER);
        productDto.setPriceBYN(PRODUCT_PRICE_BYN);
        return productDto;
    }
}
