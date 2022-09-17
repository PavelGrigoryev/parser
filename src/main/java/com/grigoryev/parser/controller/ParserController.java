package com.grigoryev.parser.controller;

import com.grigoryev.parser.job.departments.ProductParserDepartments;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Parser", description = "The Parser API")
@RequestMapping("/parser")
public class ParserController {

    private final ProductParserDepartments productParserDepartments;

    @Operation(summary = "Parse BuildingMaterials", tags = "Parser", description = "Let's parse BuildingMaterials products. This may take some time!")
    @GetMapping("/buildingMaterials")
    public ResponseEntity<String> parseBuildingMaterialsProducts() {
        productParserDepartments.parseBuildingMaterialsProducts();
        return new ResponseEntity<>("A few moments later! We parsed BuildingMaterials!", HttpStatus.OK);
    }

    @Operation(summary = "Parse HomeWare", tags = "Parser", description = "Let's parse HomeWare products. This may take some time!")
    @GetMapping("/homeWare")
    public ResponseEntity<String> parseHomeWareProducts() {
        productParserDepartments.parseHomeWareProducts();
        return new ResponseEntity<>("A few moments later! We parsed HomeWare!", HttpStatus.OK);
    }

    @Operation(summary = "Parse Instrument&Electrical", tags = "Parser", description = "Let's parse Instrument&Electrical products. This may take some time!")
    @GetMapping("/instrumentAndElectrical")
    public ResponseEntity<String> parseInstrumentAndElectricalProducts() {
        productParserDepartments.parseInstrumentAndElectricalProducts();
        return new ResponseEntity<>("A few moments later! We parsed Instrument&Electrical!", HttpStatus.OK);
    }

    @Operation(summary = "Parse WoodWork", tags = "Parser", description = "Let's parse WoodWork products. This may take some time!")
    @GetMapping("/woodWork")
    public ResponseEntity<String> parseWoodWorkProducts() {
        productParserDepartments.parseWoodWorkProducts();
        return new ResponseEntity<>("A few moments later! We parsed WoodWork!", HttpStatus.OK);
    }

    @Operation(summary = "Parse Sanitary", tags = "Parser", description = "Let's parse Sanitary products. This may take some time!")
    @GetMapping("/sanitary")
    public ResponseEntity<String> parseSanitaryProducts() {
        productParserDepartments.parseSanitaryProducts();
        return new ResponseEntity<>("A few moments later! We parsed Sanitary!", HttpStatus.OK);
    }

    @Operation(summary = "Parse Flooring", tags = "Parser", description = "Let's parse Flooring products. This may take some time!")
    @GetMapping("/flooring")
    public ResponseEntity<String> parseFlooringProducts() {
        productParserDepartments.parseFlooringProducts();
        return new ResponseEntity<>("A few moments later! We parsed Flooring!", HttpStatus.OK);
    }

    @Operation(summary = "Parse Paint", tags = "Parser", description = "Let's parse Paint products. This may take some time!")
    @GetMapping("/paint")
    public ResponseEntity<String> parsePaintProducts() {
        productParserDepartments.parsePaintProducts();
        return new ResponseEntity<>("A few moments later! We parsed Paint!", HttpStatus.OK);
    }

    @Operation(summary = "Parse DecorativeMaterials", tags = "Parser", description = "Let's parse DecorativeMaterials products. This may take some time!")
    @GetMapping("/decorativeMaterials")
    public ResponseEntity<String> parseDecorativeMaterialsProducts() {
        productParserDepartments.parseDecorativeMaterialsProducts();
        return new ResponseEntity<>("A few moments later! We parsed DecorativeMaterials!", HttpStatus.OK);
    }

    @Operation(summary = "Parse Gardening", tags = "Parser", description = "Let's parse Gardening products. This may take some time!")
    @GetMapping("/gardening")
    public ResponseEntity<String> parseGardeningProducts() {
        productParserDepartments.parseGardeningProducts();
        return new ResponseEntity<>("A few moments later! We parsed Gardening!", HttpStatus.OK);
    }
}