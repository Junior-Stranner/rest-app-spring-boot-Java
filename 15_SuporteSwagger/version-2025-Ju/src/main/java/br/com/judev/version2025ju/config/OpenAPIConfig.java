package br.com.judev.version2025ju.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()  // Instancia um objeto OpenAPI para configurar informações da documentação.
                .components(new Components())
                .info(new Info()  // Configura as informações gerais da API.
                        .title("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")// Título da API.
                        .version("v1")  // Versão da API.
                        .description("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")// Descrição breve da API.
                        .termsOfService("https://judev.com.br/meus-cursos")  // Termos de serviço da API.
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()  // Informações de contato.
                                .name("Junior Stranner")  // Nome do contato.
                                .email("Junior@spring.com")));// Endereço de e-mail do contato.
    }
}

