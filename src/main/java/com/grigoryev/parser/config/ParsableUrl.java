package com.grigoryev.parser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "parsable.url")
public class ParsableUrl {

    private String building;

    private String home;

    private String instrument;

    private String woodwork;

    private String sanitary;

    private String flooring;

    private String paint;

    private String decorative;

    private String gardening;
}
