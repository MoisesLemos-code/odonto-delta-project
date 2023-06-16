package br.com.molens.odontoDelta.application.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"br.com.molens.odontoDelta.adapter.entrypoint.controller"})
public class SwaggerConfiguration {

    @Value("${application.name}")
    private String projectName;

    @Value("${application.version}")
    private String projectVersion;

    @Autowired
    private ServletContext servletContext;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket docketSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(getDomain())
                .pathProvider(getPathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.molens.odontoDelta.adapter.entrypoint"))
                .build()
                .apiInfo(metaData())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(projectName)
                .description("Documentação da api")
                .version(projectVersion)
                .build();
    }

    private String getDomain() {
        String domain = System.getenv("DOMAIN");
        return StringUtils.isEmpty(domain) ? "localhost" : domain;
    }

    private RelativePathProvider getPathProvider() {
        return new RelativePathProvider(servletContext) {
            @Override
            public String getApplicationBasePath() {
                return "/odonto/api/";
            }
        };
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }


    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}