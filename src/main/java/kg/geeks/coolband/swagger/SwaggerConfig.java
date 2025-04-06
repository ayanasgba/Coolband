package kg.geeks.coolband.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private static final String KEY_API = "Bearer Token";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(KEY_API, apiKeySecuritySchema()))
                .info(new Info().title("Cool-Band"))
                .security(Collections.singletonList(new SecurityRequirement().addList(KEY_API)));
    }



    public SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name("Bearer token")
                .description("Please put the token!!!")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}