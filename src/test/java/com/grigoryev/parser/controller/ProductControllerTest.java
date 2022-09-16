package com.grigoryev.parser.controller;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.model.Product;
import com.grigoryev.parser.security.jwt.JwtTokenUtil;
import com.grigoryev.parser.service.ProductService;
import com.grigoryev.parser.service.impl.JwtUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final long PRODUCT_ID = 1L;

    private static final String PRODUCT_NAME = "Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)";

    private static final String PRODUCT_AMOUNT = "комп.";

    private static final String PRODUCT_MANUFACTURER = "AKS";

    private static final String PRODUCT_PRICE_BYN = "6.39";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private ProductService productService;

    @Nested
    class FindAllTests {
        @Test
        @DisplayName("check display name with empty list of elements")
        void findAll() throws Exception {
            doReturn(new ArrayList<>()).when(productService).findAll();

            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
        }

        @Test
        @DisplayName("check display name with filled list of elements")
        void findAll_with_filled_values() throws Exception {
            doReturn(List.of(getMockedProduct())).when(productService).findAll();

            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            "[" +
                                    "{" +
                                    "\"id\": 1," +
                                    "\"name\": \"Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)\"," +
                                    "\"amount\": \"комп.\"," +
                                    " \"manufacturer\": \"AKS\"," +
                                    " \"priceBYN\": \"6.39\"" +
                                    "}" +
                                    "]"
                    ));
        }
    }

    @Nested
    class FindByIdTest {
        @Test
        @DisplayName("check findById with exist element")
        void findById_with_exist_element() throws Exception {
            doReturn(getMockedProductDto()).when(productService).findById(PRODUCT_ID);

            mockMvc.perform(get("/products/" + PRODUCT_ID))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            "{" +
                                    "\"id\": 1," +
                                    "\"name\": \"Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)\"," +
                                    "\"amount\": \"комп.\"," +
                                    " \"manufacturer\": \"AKS\"," +
                                    " \"priceBYN\": \"6.39\"" +
                                    "}"
                    ));
        }
    }

    @Nested
    class SaveTests {
        @Test
        @DisplayName("check save method")
        void save() throws Exception {
            doReturn(getMockedProduct()).when(productService)
                    .save(any(ProductDto.class));

            mockMvc.perform(post("/products")
                            .content(
                                    "{" +
                                            "\"id\": 1," +
                                            "\"name\": \"Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)\"," +
                                            "\"amount\": \"комп.\"," +
                                            " \"manufacturer\": \"AKS\"," +
                                            " \"priceBYN\": \"6.39\"" +
                                            "}"
                            )
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(
                            "{" +
                                    "\"id\": 1," +
                                    "\"name\": \"Накладка для мебельных петель с подсветкой (накладка,крепление,батарейка)\"," +
                                    "\"amount\": \"комп.\"," +
                                    " \"manufacturer\": \"AKS\"," +
                                    " \"priceBYN\": \"6.39\"" +
                                    "}"
                    ));
        }

        @Test
        @DisplayName("check save method with bad request")
        void save_with_bad_request() throws Exception {
            doReturn(getMockedProduct()).when(productService)
                    .save(any(ProductDto.class));

            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class DeleteTest {
        @Test
        @DisplayName("check delete method")
        void delete() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + PRODUCT_ID))
                    .andExpect(status().isOk());
        }
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
