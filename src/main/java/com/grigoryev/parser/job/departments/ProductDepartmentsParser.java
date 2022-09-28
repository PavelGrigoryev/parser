package com.grigoryev.parser.job.departments;

import com.grigoryev.parser.exception.NoSuchDepartmentException;
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
public class ProductDepartmentsParser {

    private final ProductParser productParser;

    @Value("${parsable.building.materials.url}")
    private String buildingMaterialsUrl;

    @Value("${number.of.building.materials.pages}")
    private int buildingMaterialsNumberOfPages;

    @Value("${parsable.homeware.url}")
    private String homeWareUrl;

    @Value("${number.of.homeware.pages}")
    private int homeWareNumberOfPages;

    @Value("${parsable.instrument&electrical.url}")
    private String instrumentAndElectricalUrl;

    @Value("${number.of.instrument&electrical.pages}")
    private int instrumentAndElectricalNumberOfPages;

    @Value("${parsable.woodwork.url}")
    private String woodWorkUrl;

    @Value("${number.of.woodwork.pages}")
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

    @Value("${parsable.decorative.materials.url}")
    private String decorativeMaterialsUrl;

    @Value("${number.of.decorative.materials.pages}")
    private int decorativeMaterialsNumberOfPages;

    @Value("${parsable.gardening.url}")
    private String gardeningUrl;

    @Value("${number.of.gardening.pages}")
    private int gardeningNumberOfPages;

    public void enterNumberOfDepartmentToParse(Integer departmentNumber) {
        switch (departmentNumber) {
            case 1 -> parseBuildingMaterialsProducts();
            case 2 -> parseHomeWareProducts();
            case 3 -> parseInstrumentAndElectricalProducts();
            case 4 -> parseWoodWorkProducts();
            case 5 -> parseSanitaryProducts();
            case 6 -> parseFlooringProducts();
            case 7 -> parsePaintProducts();
            case 8 -> parseDecorativeMaterialsProducts();
            case 9 -> parseGardeningProducts();
            default -> throw new NoSuchDepartmentException("There is no such department with №" + departmentNumber +
                    ". Enter the correct department number please, for example: 1, 2, 3, 4, 5, 6, 7, 8, 9");
        }
    }

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
