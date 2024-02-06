package br.com.jujubaprojects.restappspringboot.Integrationtests.swagger;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jujubaprojects.restappspringboot.configs.TestConfigs;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest {
    
	// Teste para verificar se a página do Swagger UI é exibida corretamente
@Test
public void shouldDisplaySwaggerUiPage() {
    // Envia uma solicitação GET para a página do Swagger UI e verifica se a resposta é bem-sucedida (código 200)
    var content =
        given()
            .basePath("/swagger-ui/index.html") // Define o caminho base para a página do Swagger UI
            .port(TestConfigs.SERVER_PORT) // Define a porta do servidor a ser usada nos testes
        .when()
            .get()
        .then()
            .statusCode(200) // Verifica se o status da resposta é 200 (OK)
        .extract()
            .body()
                .asString(); // Extrai o conteúdo da resposta como uma string

    // Verifica se o conteúdo da página contém o título "Swagger UI"
    assertTrue(content.contains("Swagger UI"));
}

}
