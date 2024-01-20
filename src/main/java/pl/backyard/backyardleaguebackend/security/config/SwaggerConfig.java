package pl.backyard.backyardleaguebackend.security.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@Controller
@AllArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi dockerImageGuardApi() {
        return GroupedOpenApi.builder()
                .group("01-backyard-league")
                .packagesToScan("pl.backyard")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(customerGlobalHeaderOpenApiController())
                .build();
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiController() {
        var securitySchemaName = "bearer-auth";
        return openApi -> openApi.info(getInfo())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemaName))
                .getComponents()
                .addSecuritySchemes(securitySchemaName, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }

    private Info getInfo() {
        return new Info()
                .title("backyard-league")
                .description("backyard-league")
                .version("1.0");
    }

}
