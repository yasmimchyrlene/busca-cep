package com.integration.buscacep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Busca Cep")
                .version("1.0,0")
                .license("Lincença: GPLv3")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
                .contact(new Contact("Yasmim Chyrlene", "https://github.com/yasmimchyrlene",
                        "yasmimchyrlene@hotmail.com"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .tags(new Tag("Gerenciador", "API de gerenciamento de CEP."))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.integration.buscacep.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}

