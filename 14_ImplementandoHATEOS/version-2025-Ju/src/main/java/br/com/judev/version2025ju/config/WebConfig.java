package br.com.judev.version2025ju.config;

import br.com.judev.version2025ju.serializer.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // https://www.baeldung.com/spring-mvc-content-negotiation-json-xml

        // Via EXTENSION. http://localhost:8080/api/person/v1/2.xml or http://localhost:8080/api/person/v1/2.JSON Deprecated on Spring Boot 2.6

        // Via QUERY PARAM http://localhost:8080/api/person/v1/2?mediaType=xml
        /**
         configurer.favorParameter(true)
         .parameterName("mediaType")
         .ignoreAcceptHeader(true)
         .useRegisteredExtensionsOnly(false)
         .defaultContentType(MediaType.APPLICATION_JSON)
         .mediaType("json", MediaType.APPLICATION_JSON)
         .mediaType("xml", MediaType.APPLICATION_XML);
         */

        // Via HEADER PARAM http://localhost:8080/api/person/v1/2
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MediaType.APPLICATION_YAML);
    }

}
