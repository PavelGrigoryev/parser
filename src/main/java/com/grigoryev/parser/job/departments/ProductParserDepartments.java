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

    @Value("${parsable.instrument&electrical.url}")
    private String instrumentAndElectricalUrl;

    @Value("${number.of.instrument&electrical.pages}")
    private int instrumentAndElectricalNumberOfPages;

    @Value("${parsable.woodWork.url}")
    private String woodWorkUrl;

    @Value("${number.of.woodWork.pages}")
    private int woodWorkNumberOfPages;

    public void parseBuildingMaterialsProducts() {
        productParser.parseProducts(buildingMaterialsUrl, buildingMaterialsNumberOfPages);
    }

    public void parseHomeWareProducts() {
        productParser.parseProducts(homeWareUrl, homeWareNumberOfPages);
    }

    public void parseInstrumentAndElectricalProducts() {
        productParser.parseProducts(instrumentAndElectricalUrl, instrumentAndElectricalNumberOfPages);
    }

    public void parseWoodWorkProducts() {
        productParser.parseProducts(woodWorkUrl, woodWorkNumberOfPages);
    }
}
