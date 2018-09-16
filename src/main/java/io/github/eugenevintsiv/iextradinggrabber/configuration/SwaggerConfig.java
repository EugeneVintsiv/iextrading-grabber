package io.github.eugenevintsiv.iextradinggrabber.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Grabber for IexTrading platform")
                .description("Provides an access to the IexTrading Grabber resources")
                .version("0.0.1")
                .build();
    }

    @Bean
    public Docket apiConf() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("io.github.eugenevintsiv.iextradinggrabber"))
                .paths(PathSelectors.ant("/api/v0/**"))
                .build();
    }

}
