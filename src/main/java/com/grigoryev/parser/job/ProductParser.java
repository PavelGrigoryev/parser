package com.grigoryev.parser.job;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class ProductParser {

    private final ProductService productService;

    public void parseProducts(String url, Integer numberOfPages) {

        for (int j = 1; j < numberOfPages; j++) {
            log.info("Parsing page №{} ...", j);
            try {
                Document document = Jsoup.connect(url + j)
                        .userAgent("Chrome")
                        .timeout(30000)
                        .referrer("https://google.com")
                        .get();

                Elements divs = document.select("div.table");

                List<ProductDto> productDtoList = new ArrayList<>();

                findElementsByDivs(divs, productDtoList);

            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void findElementsByDivs(Elements divs, List<ProductDto> productDtoList) {
        for (Element element : divs) {
            findElementsByTds(productDtoList, element);
        }
    }

    private void findElementsByTds(List<ProductDto> productDtoList, Element element) {
        for (int i = 0; i < element.select("div.td_name").size(); i++) {
            String name = element.select("div.td_name").get(i).text();
            String manufacturer = element.select("div.td_proizv").get(i).text();
            String amount = element.select("div.td_nalich").get(i).text();
            String price = element.select("div.td_price").get(i).text();
            checkIfProductNameIsInDB(productDtoList, name, manufacturer, amount, price);
        }
        productService.saveAll(productDtoList);
    }

    private void checkIfProductNameIsInDB(List<ProductDto> productDtoList, String name, String manufacturer, String amount, String price) {
        if (productService.findByName(name).isEmpty()) {
            ProductDto productDto = new ProductDto();
            productDto.setName(name);
            productDto.setManufacturer(manufacturer);
            productDto.setAmount(amount);
            productDto.setPriceBYN(price);
            productDtoList.add(productDto);
            log.warn("Adding new product : " + productDto.getName());
        }
    }

}
