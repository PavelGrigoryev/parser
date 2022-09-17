package com.grigoryev.parser.controller;

import com.grigoryev.parser.exception.NoSuchDepartmentException;
import com.grigoryev.parser.job.departments.ProductParserDepartments;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Parser", description = "The Parser API")
@RequestMapping("/parser")
public class ParserController {

    private final ProductParserDepartments productParserDepartments;

    @Operation(summary = "Choose number of department to parse", tags = "Parser", description = "Let's parse chosen products. This may take some time!")
    @Parameter(name = "department", description = "Enter a number of department that you want to parse from 1 to 9", example = "2")
    @GetMapping
    public ResponseEntity<String> parseAllProducts(@RequestParam String department) {
        switch (department) {
            case "1" -> productParserDepartments.parseBuildingMaterialsProducts();
            case "2" -> productParserDepartments.parseHomeWareProducts();
            case "3" -> productParserDepartments.parseInstrumentAndElectricalProducts();
            case "4" -> productParserDepartments.parseWoodWorkProducts();
            case "5" -> productParserDepartments.parseSanitaryProducts();
            case "6" -> productParserDepartments.parseFlooringProducts();
            case "7" -> productParserDepartments.parsePaintProducts();
            case "8" -> productParserDepartments.parseDecorativeMaterialsProducts();
            case "9" -> productParserDepartments.parseGardeningProducts();
            default -> throw new NoSuchDepartmentException("There is no such department with №" + department +
                    ". Enter the correct department number please, for example: 1, 2, 3, 4, 5, 6, 7, 8, 9");
        }
        return new ResponseEntity<>("A few moments later! We parsed department №" + department, HttpStatus.OK);
    }
}
