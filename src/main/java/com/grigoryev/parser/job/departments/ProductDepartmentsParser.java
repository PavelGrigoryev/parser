package com.grigoryev.parser.job.departments;

import com.grigoryev.parser.config.NumberOfParsablePages;
import com.grigoryev.parser.config.ParsableUrl;
import com.grigoryev.parser.exception.NoSuchDepartmentException;
import com.grigoryev.parser.job.ProductParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class ProductDepartmentsParser {

    private final ProductParser productParser;

    private final ParsableUrl parsableUrl;

    private final NumberOfParsablePages numberOfParsablePages;

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
        productParser.parseProducts(parsableUrl.getBuilding(), numberOfParsablePages.getBuilding());
    }

    public void parseHomeWareProducts() {
        productParser.parseProducts(parsableUrl.getHome(), numberOfParsablePages.getHome());
    }

    public void parseInstrumentAndElectricalProducts() {
        productParser.parseProducts(parsableUrl.getInstrument(), numberOfParsablePages.getInstrument());
    }

    public void parseWoodWorkProducts() {
        productParser.parseProducts(parsableUrl.getWoodwork(), numberOfParsablePages.getWoodwork());
    }

    public void parseSanitaryProducts() {
        productParser.parseProducts(parsableUrl.getSanitary(), numberOfParsablePages.getSanitary());
    }

    public void parseFlooringProducts() {
        productParser.parseProducts(parsableUrl.getFlooring(), numberOfParsablePages.getFlooring());
    }

    public void parsePaintProducts() {
        productParser.parseProducts(parsableUrl.getPaint(), numberOfParsablePages.getPaint());
    }

    public void parseDecorativeMaterialsProducts() {
        productParser.parseProducts(parsableUrl.getDecorative(), numberOfParsablePages.getDecorative());
    }

    public void parseGardeningProducts() {
        productParser.parseProducts(parsableUrl.getGardening(), numberOfParsablePages.getGardening());
    }
}
