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

    @Value("${parsable.sanitary.url}")
    private String sanitaryUrl;

    @Value("${number.of.sanitary.pages}")
    private int sanitaryNumberOfPages;

    @Value("${parsable.flooring.url}")
    private String flooringUrl;

    @Value("${number.of.flooring.pages}")
    private int flooringNumberOfPages;

    @Value("${parsable.paint.url}")
    private String paintUrl;

    @Value("${number.of.paint.pages}")
    private int paintNumberOfPages;

    @Value("${parsable.decorativeMaterials.url}")
    private String decorativeMaterialsUrl;

    @Value("${number.of.decorativeMaterials.pages}")
    private int decorativeMaterialsNumberOfPages;

    @Value("${parsable.gardening.url}")
    private String gardeningUrl;

    @Value("${number.of.gardening.pages}")
    private int gardeningNumberOfPages;

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

    public void parseSanitaryProducts() {
        productParser.parseProducts(sanitaryUrl, sanitaryNumberOfPages);
    }

    public void parseFlooringProducts() {
        productParser.parseProducts(flooringUrl, flooringNumberOfPages);
    }

    public void parsePaintProducts() {
        productParser.parseProducts(paintUrl, paintNumberOfPages);
    }

    public void parseDecorativeMaterialsProducts() {
        productParser.parseProducts(decorativeMaterialsUrl, decorativeMaterialsNumberOfPages);
    }

    public void parseGardeningProducts() {
        productParser.parseProducts(gardeningUrl, gardeningNumberOfPages);
    }
}
