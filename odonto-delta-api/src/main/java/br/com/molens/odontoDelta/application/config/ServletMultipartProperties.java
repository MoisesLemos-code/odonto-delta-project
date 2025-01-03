package br.com.molens.odontoDelta.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.servlet.multipart")
public class ServletMultipartProperties {

    @Getter
    @Setter
    private String maxFileSize;

}
