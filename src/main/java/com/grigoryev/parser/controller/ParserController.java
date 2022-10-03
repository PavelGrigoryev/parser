package com.grigoryev.parser.controller;

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
    @Parameter(name = "departmentNumber", description = "Enter a number of department that you want to parse from 1 to 9", example = "3")
    @GetMapping
    public ResponseEntity<String> parseAllProducts(@RequestParam("departmentNumber") Integer departmentNumber) {
        productDepartmentsParser.enterNumberOfDepartmentToParse(departmentNumber);
        return new ResponseEntity<>("A few moments later! We parsed department №" + departmentNumber, HttpStatus.OK);
    }
}
