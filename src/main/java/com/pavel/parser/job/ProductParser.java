package com.pavel.parser.job;

import com.pavel.parser.model.Product;
import com.pavel.parser.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Component
public class ProductParser {

    @Autowired
    ProductService productService;

    @Transactional
    @Scheduled(fixedDelay = 600000)
    public void parseProducts() {

        for (int j = 1; j < 11; j++) {
            String url = "https://diy.by/catalog/homeware/?PAGECOUNT=80&FIRSTLEVID=homeware&PAGEN_1=" + j;

            try {
                Document document = Jsoup.connect(url)
                        .userAgent("Chrome")
                        .timeout(5000)
                        .referrer("https://google.com")
                        .get();

                Elements divs = document.select("div.table");

                for (Element element : divs) {
                    for (int i = 0; i < 80; i++) {
                        String name = element.select("div.td_name").get(i).text();
                        String manufacturer = element.select("div.td_proizv").get(i).text();
                        String amount = element.select("div.td_nalich").get(i).text();
                        String price = element.select("div.td_price").get(i).text();
                        if (Boolean.FALSE.equals(productService.isExist(name))) {
                            Product product = new Product();
                            product.setName(name);
                            product.setManufacturer(manufacturer);
                            product.setAmount(amount);
                            product.setPriceBYN(price);
                            productService.save(product);
                            log.info(String.valueOf(product));
                        }
                    }
                }

            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
