package com.grigoryev.parser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "number.of.parsable.pages")
public class NumberOfParsablePages {

    private int building;

    private int home;

    private int instrument;

    private int woodwork;

    private int sanitary;

    private int flooring;

    private int paint;

    private int decorative;

    private int gardening;
}
