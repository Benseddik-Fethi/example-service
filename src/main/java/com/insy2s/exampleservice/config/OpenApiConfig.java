package com.insy2s.exampleservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Example Service",
                version = "1.0",
                description = "Example Service Fethi",
                contact = @Contact(
                        name = "Fethi Benseddik",
                        url = "dev.benseddik@gmail.com",
                        email = "fbenseddik@insy2s.fr"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production server",
                        url = "http://localhost:8081"
                )
        }
)
public class OpenApiConfig {
}
