package com.luinel.beprepared.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "BePrepared",
                description = "Uma app permite aos usuários se " +
                        "materem informados e seguros durante desastres naturaís",
                version = "1.0",
                contact = @Contact(
                        name = "Luinel Sitoe",
                        email = "luinelsitoe@gmail.com",
                        url = "https://github.com/luinelsitoe"
                ),
                license = @License(
                        name = "GPLv3",
                        url = "https://www.gnu.org/licenses/licenses.html"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Faça o login na API, para poder usar perfeitamente a applicação BePrepared," +
                " coloque o seu token de authenticação no campo abaixo e clique no botão authorize",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
