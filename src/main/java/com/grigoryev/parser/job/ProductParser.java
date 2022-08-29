package com.grigoryev.parser.job;

import com.grigoryev.parser.dto.ProductDto;
import com.grigoryev.parser.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductParser {

    private final ProductService productService;

    @Value("${parsable.url}")
    private String url;

    @Value("${number.of.pages}")
    private int numberOfPages;

    @Value("${number.of.products}")
    private int numberOfProducts;

    @Transactional
    @Scheduled(fixedDelay = 600000)
    public void parseProducts() {

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

                for (Element element : divs) {
                    for (int i = 0; i < numberOfProducts; i++) {
                        String name = element.select("div.td_name").get(i).text();
                        String manufacturer = element.select("div.td_proizv").get(i).text();
                        String amount = element.select("div.td_nalich").get(i).text();
                        String price = element.select("div.td_price").get(i).text();
                        if (productService.findByName(name).isEmpty()) {
                            ProductDto productDto = new ProductDto();
                            productDto.setName(name);
                            productDto.setManufacturer(manufacturer);
                            productDto.setAmount(amount);
                            productDto.setPriceBYN(price);
                            productDtoList.add(productDto);
                        }
                    }
                    productService.saveAll(productDtoList);
                }

            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
