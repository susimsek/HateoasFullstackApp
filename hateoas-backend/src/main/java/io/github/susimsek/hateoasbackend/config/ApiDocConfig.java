package io.github.susimsek.hateoasbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ApiDocProperties.class)
public class ApiDocConfig {

    @Bean
    public OpenAPI customOpenAPI(ApiDocProperties apiDocProperties) {
        return new OpenAPI()
                .components(new Components())
                .info(metaData(apiDocProperties));
    }

    private Info metaData(ApiDocProperties apiDocProperties) {
        return new Info()
                .title(apiDocProperties.getTitle())
                .description(apiDocProperties.getDescription())
                .version(apiDocProperties.getVersion())
                .termsOfService(apiDocProperties.getTermsOfServiceUrl())
                .license(new License()
                        .name(apiDocProperties.getLicense())
                        .url(apiDocProperties.getLicenseUrl()));
    }

    @Bean
    public OperationCustomizer customize() {
        StringSchema languageSchema = new StringSchema();
        languageSchema.setEnum(List.of("en", "tr"));
        Parameter language = new Parameter()
                .name("Accept-Language")
                .description("Accept Language Header")
                .schema(languageSchema)
                .example("en")
                .in("header")
                .required(false);
        return (operation, handlerMethod) -> operation.addParametersItem(language);
    }
}