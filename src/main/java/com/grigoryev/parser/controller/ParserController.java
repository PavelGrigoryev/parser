package com.grigoryev.parser.controller;

import com.grigoryev.parser.exception.NoSuchDepartmentException;
import com.grigoryev.parser.job.departments.ProductDepartmentsParser;
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

    private final ProductDepartmentsParser productDepartmentsParser;

    @Operation(summary = "Choose number of department to parse", tags = "Parser", description = "Let's parse chosen products. This may take some time!")
    @Parameter(name = "departmentNumber", description = "Enter a number of department that you want to parse from 1 to 9", example = "2")
    @GetMapping
    public ResponseEntity<String> parseAllProducts(@RequestParam("departmentNumber") Integer departmentNumber) {
        switch (departmentNumber) {
            case 1 -> productDepartmentsParser.parseBuildingMaterialsProducts();
            case 2 -> productDepartmentsParser.parseHomeWareProducts();
            case 3 -> productDepartmentsParser.parseInstrumentAndElectricalProducts();
            case 4 -> productDepartmentsParser.parseWoodWorkProducts();
            case 5 -> productDepartmentsParser.parseSanitaryProducts();
            case 6 -> productDepartmentsParser.parseFlooringProducts();
            case 7 -> productDepartmentsParser.parsePaintProducts();
            case 8 -> productDepartmentsParser.parseDecorativeMaterialsProducts();
            case 9 -> productDepartmentsParser.parseGardeningProducts();
            default -> throw new NoSuchDepartmentException("There is no such department with №" + departmentNumber +
                    ". Enter the correct department number please, for example: 1, 2, 3, 4, 5, 6, 7, 8, 9");
        }
        return new ResponseEntity<>("A few moments later! We parsed department №" + departmentNumber, HttpStatus.OK);
    }
}
