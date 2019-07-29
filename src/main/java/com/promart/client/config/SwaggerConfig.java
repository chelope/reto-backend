package com.promart.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger Configuration Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/clientes/*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Client API",
                "API.",
                "v1.0",
                "Terms of service",
                new Contact("Christian Arias", "www.christian.arias.com", "chri.arias@gmail.com"),
                "GPL", "http://licence", Collections.emptyList());
        return apiInfo;
    }
}
