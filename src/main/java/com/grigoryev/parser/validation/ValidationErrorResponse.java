package com.grigoryev.parser.validation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {

}
