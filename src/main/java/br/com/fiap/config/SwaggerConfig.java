package br.com.fiap.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "AquaGuard API", version = "v1", description = "API de monitoramento AquaGuard")
)
@Configuration
public class SwaggerConfig { }