package com.pavel.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "https://diy.by/catalog/homeware/?PAGECOUNT=80&FIRSTLEVID=homeware&PAGEN_1=4";

        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements divs = document.select("div[class=table]");

            for (Element div : divs) {
                List<String> name = div.select("div[class=td_name]").eachText();
                name.forEach(System.out::println);
              /*  String proizv = div.select("div[class=td_proizv]").text();
                String nalich = div.select("div[class=td_nalich]").text();
                String price = div.select("div[class=td_price]").text();
                String inStock = div.select("div[class=td_instock]").text();
                System.out.println(name);
                System.out.println(proizv);
                System.out.println(nalich);
                System.out.println(price);
                System.out.println(inStock);*/
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
