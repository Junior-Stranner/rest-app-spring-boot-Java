package br.com.jujubaprojects.restappspringboot.Integrationtests.controller.withjson;

/*import static org.junit.jupiter.api.Assertions.assertTrue;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;



import br.com.jujubaprojects.restappspringboot.configs.TestConfigs;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class) // Define a ordem de execução dos testes
public class PersonControllerJsonTest {

// Objeto para configuração de requisições
private static RequestSpecification specification;

// Objeto para serialização/desserialização JSON
private static ObjectMapper objectMapper;

// Objeto representando uma pessoa (Value Object)
private static PersonVO personVO;

// Configuração inicial antes da execução dos testes
@BeforeAll
public static void setup() {
    objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Ignora propriedades desconhecidas no JSON

    personVO = new PersonVO();
}

// Teste de criação de pessoa
@Test
@Order(1)
public void testCreate() throws JsonMappingException, JsonProcessingException {
    mockPerson(); // Cria um objeto PersonVO com dados de teste

    // Cria a configuração da requisição
    specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_JUJUBAPROJECTS) // Adiciona um cabeçalho de origem
            .setBasePath("/api/person/v1") // Define o caminho base da API
            .setPort(TestConfigs.SERVER_PORT) // Define a porta do servidor
            .addFilter(new RequestLoggingFilter(LogDetail.ALL)) // Registra detalhes da requisição
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL)) // Registra detalhes da resposta
            .build();

    // Envia a requisição POST para criar a pessoa
    var content = given().spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON) // Define o tipo de conteúdo como JSON
            .body(personVO) // Envia o objeto PersonVO no corpo da requisição
            .when()
            .post() // Envia a requisição POST
            .then()
            .statusCode(200) // Espera um código de status 200 (sucesso)
            .extract()
            .body()
            .asString(); // Extrai o corpo da resposta como string

    // Deserializa o JSON da resposta para um objeto PersonVO
    PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
    personVO = persistedPerson; // Atualiza o objeto personVO com a pessoa persistida

    // Verifica se a pessoa foi criada corretamente
    // ... (demais asserções do teste)
	assertNotNull(persistedPerson); // Garante que o objeto de pessoa persistida não é nulo
		
    // Verifica se cada atributo do objeto de pessoa persistida não é nulo
    assertNotNull(persistedPerson.getKey());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());
		
    // Verifica se a chave (key) da pessoa persistida é maior que zero
    assertTrue(persistedPerson.getKey() > 0);
		
    // Verifica se os valores dos atributos correspondem aos valores esperados
    assertEquals("Bubu", persistedPerson.getFirstName());
    assertEquals("fornari", persistedPerson.getLastName());
    assertEquals("Rio de Janeiro", persistedPerson.getAddress());
    assertEquals("female", persistedPerson.getGender());
}

// Teste para criar uma pessoa com origem incorreta e verificar se é tratado corretamente
@Test
@Order(2)
public void testCreateWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
    mockPerson(); // Prepara os dados de pessoa para o teste
		
    // Configura a especificação para a solicitação com a origem incorreta
    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_JUJUBAPROJECTS)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();
		
    // Envia a solicitação POST com a origem incorreta e verifica se é recebido um status de erro 403
    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .body(personVO)
        .when()
        .post()
        .then()
        .statusCode(403)
        .extract()
        .body()
        .asString();
		
    // Verifica se a mensagem de erro recebida corresponde à mensagem esperada
    assertNotNull(content);
    assertEquals("Invalid CORS request", content);
}

// Teste para buscar uma pessoa pelo ID e verificar se os dados estão corretos
@Test
@Order(3)
public void testFindById() throws JsonMappingException, JsonProcessingException {
    mockPerson(); // Prepara os dados de pessoa para o teste
		
    // Configura a especificação para a solicitação de busca por ID
    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_JUJUBAPROJECTS)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();
		
    // Envia a solicitação GET com o ID da pessoa e verifica se é recebido um status de sucesso 200
    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .pathParam("id", personVO.getKey())
        .when()
        .get("{id}")
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();
		
    // Converte a resposta em um objeto PersonVO e atualiza o objeto personVO
    PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
    personVO = persistedPerson;
		
    // Verifica se o objeto de pessoa persistida não é nulo e contém todos os atributos necessários
    assertNotNull(persistedPerson);
    assertNotNull(persistedPerson.getKey());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());
		
    // Verifica se a chave (key) da pessoa persistida é maior que zero
    assertTrue(persistedPerson.getKey() > 0);
		
    // Verifica se os valores dos atributos correspondem aos valores esperados
    assertEquals("Richard", persistedPerson.getFirstName());
    assertEquals("Stallman", persistedPerson.getLastName());
    assertEquals("New York City, New York, US", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
}

	private void mockPerson() {
		personVO.setFirstName("Bubu");
		personVO.setLastName("Fornari");
		personVO.setAddress("Rio de Janeiro");
		personVO.setGender("female");
	}
}*/
