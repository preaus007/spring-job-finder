package com.touhed.hrm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "HRM API Documentation",
                description = "API documentation for Human Resource Management Spring Boot Application",
                contact = @Contact(
                        name = "MD Touhedul Islam",
                        email = "touhedcs@gmail.com"
                ),
                version = "0.1.0"
        )
)
public class OpenApiDocConfig {
}
