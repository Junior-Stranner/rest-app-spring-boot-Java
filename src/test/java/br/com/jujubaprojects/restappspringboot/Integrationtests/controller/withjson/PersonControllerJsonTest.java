package br.com.jujubaprojects.restappspringboot.Integrationtests.controller.withjson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest  {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static PersonVO personVO;

	@BeforeAll
	public static void setup(){
        objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		personVO = new PersonVO();
	}
    
	@Test
	@Order(1)
	public void testCreate() {
		mockPerson();

		specification = new RequestSpecBuilder()
		   .addHeader(TestConfigs.HEADER_PARAM_ORIGIN,"https://jujubaprojects.com.br")
		   .setBasePath("/api/person/v1")
		   .setPort(TestConfigs.SERVER_PORT)
		       .addFilter(new RequestLoggingFilter(LogDetail.ALL))
			   .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
		   .build();

		   var content = given().spec(specification)
		   .contentType(TestConfigs.CONTENT_TYPE_JSON)
			   .body(personVO)
			   .when()
			   .post()
		   .then()
			   .statusCode(200)
				   .extract()
				   .body()
					   .asString();

			PersonVO persistedPerson = new objectMapper.readValue(content,PersonVO.class);   
			personVO = persistedPerson;
		
		   assertNotNull(persistedPerson);
		
		   assertNotNull(persistedPerson.getKey());
		   assertNotNull(persistedPerson.getFirstName());
		   assertNotNull(persistedPerson.getLastName());
		   assertNotNull(persistedPerson.getAddress());
		   assertNotNull(persistedPerson.getGender());
		
		   assertTrue(persistedPerson.getKey() > 0);
		
		   assertEquals("Bubu", persistedPerson.getFirstName());
		   assertEquals("fornari", persistedPerson.getLastName());
		   assertEquals("Rio de Janeiro", persistedPerson.getAddress());
		   assertEquals("female", persistedPerson.getGender());

	}

	private void mockPerson() {
		personVO.setFirstName("Bubu");
		personVO.setLastName("Fornari");
		personVO.setAddress("Rio de Janeiro");
		personVO.setGender("female");
	}
}
