package com.universidad.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "API-Registro Universitario",
                description = "API REST para el registro de materias, estudiantes y docentes",
                version = "2.0.0",
                contact = @Contact(
                        name = "Jose Alejandro Fernandez Sanchez",
                        email = "jfernandezs2@fcpn.edu.bo"
                ),
                license = @License(
                        name = "Standard Software Use License",
                        url = "https://www.opensource.org/licenses/ssu"
                )
        ),
        servers = {
                @Server(
                        description = "DEV-SERVIDOR",
                        url = "http://localhost:8080"
                )
        },
        security = @SecurityRequirement(name = "Security Token")

)

//Cofiguracin del Esquema de seguridad, para que pueda funcionar el Swagger
@SecurityScheme(
        name = "Security Token",
        description = "Token de Acceso de la API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
