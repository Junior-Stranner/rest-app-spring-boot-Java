package br.com.jujubaprojects.restappspringboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpedinAi {
    
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()  // Instancia um objeto OpenAPI para configurar informações da documentação.
            .info(new Info()  // Configura as informações gerais da API.
                .title("RESTful API with Java 17 and Spring Boot 3")  // Título da API.
                .version("v1")  // Versão da API.
                .description("Some description about your API")  // Descrição breve da API.
                .termsOfService("https://pub.erudio.com.br/meus-cursos")  // Termos de serviço da API.
                .license(new License()  // Informações sobre a licença da API.
                    .name("Apache 2.0")  // Nome da licença.
                    .url("https://pub.erudio.com.br/meus-cursos"))  // URL da licença.
                .contact(new Contact()  // Informações de contato.
                    .name("Junior Stranner")  // Nome do contato.
                    .email("Junior@spring-park.com")));  // Endereço de e-mail do contato.
    }
    
}
