package graber.thomas.feastverse.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "FeastVerse API",
                version = "v1",
                description = "Create and save cooking recipes. Allows you to have ideas and comment on recipes.",
                contact = @Contact(name = "Thomas GRABER", email = "htomas.graber.pro@gmail.com")
        ),
        security = @SecurityRequirement(name = "bearerAuth"),
        servers = {
                @Server(url = "/api/v1", description = "Default Server URL")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
