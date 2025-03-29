package backend_principal_tienda.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Productos")
                        .version("1.0")
                        .description("Documentación de los endpoints disponibles"));
    }
}