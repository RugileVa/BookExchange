package BookExchange.BookCrosser.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8080")},
        info = @Info(
                title = "API",
                description = "Oauth2-secured API",
                version = "v1.0"
        ),
        security = @SecurityRequirement(name = "oauth2", scopes = {"openid"})
)
@SecuritySchemes({
        @SecurityScheme(
                name = "oauth2",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        ),
        @SecurityScheme(
                name = "session",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.COOKIE,
                paramName = "SESSION"
        )
})
public class OpenApiConfiguration {
        // Any additional configurations or code can be added here
}