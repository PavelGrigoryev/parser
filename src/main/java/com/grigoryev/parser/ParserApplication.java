package com.grigoryev.parser;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Amazing Product Parser",
                version = "1.0.0",
                description = "Make life easier by parsing this site.",
                license = @License(name = "Mayan Indians License 3.0"),
                contact = @Contact(
                        name = "Author: Grigoryev Pavel",
                        url = "https://pavelgrigoryev.github.io/GrigoryevPavel/"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

}
