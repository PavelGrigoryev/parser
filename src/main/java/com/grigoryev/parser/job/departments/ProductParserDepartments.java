package com.grigoryev.parser.job.departments;

import com.grigoryev.parser.job.ProductParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ProductParserDepartments {

    private final ProductParser productParser;

    @Value("${parsable.buildingMaterials.url}")
    private String buildingMaterialsUrl;

    @Value("${number.of.buildingMaterials.url}")
    private int buildingMaterialsNumberOfPages;

    @Value("${parsable.homeWare.url}")
    private String homeWareUrl;

    @Value("${number.of.homeWare.pages}")
    private int homeWareNumberOfPages;

    public void parseBuildingMaterialsProducts() {
        productParser.parseProducts(buildingMaterialsUrl, buildingMaterialsNumberOfPages);
    }

    public void parseHomeWareProducts() {
        productParser.parseProducts(homeWareUrl, homeWareNumberOfPages);
    }

}
