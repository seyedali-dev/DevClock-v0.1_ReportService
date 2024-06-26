package com.seyed.ali.reportsservice.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Keycloak",
        openIdConnectUrl = "http://localhost:8080/realms/DevVault-v2.0/.well-known/openid-configuration",
        scheme = "bearer",
        type = SecuritySchemeType.OPENIDCONNECT,
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        info = @Info(
                title = "Report",
                version = "0.1",
                summary = "Summary :: Report-Service microservice.",
                description = "Description :: Report-Service microservice.",
                contact = @Contact(
                        name = "SeyedAli",
                        email = "seyed.ali.devl@gmail.com",
                        url = "https://github.com/seyedali-dev"
                )
        )
)
public class SwaggerConfiguration {
}
