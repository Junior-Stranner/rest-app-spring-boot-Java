/*package br.com.jujubaprojects.restappspringboot.unitests.Mockito.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.Repositories.PersonRepository;
import br.com.jujubaprojects.restappspringboot.Service.person.PersonService;
import br.com.jujubaprojects.restappspringboot.UnitTests.mapper.Mocks.MockPerson;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    MockPerson input;
    	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	PersonRepository personRepository;


	@BeforeEach
    void setUpMocks() throws Exception {
    // Inicializando a instância de MockPerson para ser usada nos testes
    input = new MockPerson();
    
    // Inicializando os mocks para este teste
    MockitoAnnotations.openMocks(this);
}

    @Test
     void testFindById() {
    // Criando uma instância de Person simulada para o teste
    Person person = input.mockEntity(1); 
    person.setId(1L);
    
    // Configurando o comportamento do repositório mockado ao chamar o método findById
    when(personRepository.findById(1L)).thenReturn(Optional.of(person));
    
    // Chamando o método do serviço e verificando o resultado
    var result = personService.findById(1L);
    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
}

    @Test
    void testCreate() throws Exception {
    // Criando uma instância de Person simulada para o teste
    Person entity = input.mockEntity(1); 
    entity.setId(1L);
    
    // Criando uma instância de Person simulada após a persistência
    Person persisted = entity;
    persisted.setId(1L);
    
    // Criando uma instância de PersonVO simulada
    PersonVO vo = input.mockVO(1);
    vo.setKey(1L);
    
    // Configurando o comportamento do repositório mockado ao chamar o método save
    when(personRepository.save(entity)).thenReturn(persisted);
    
    // Chamando o método do serviço e verificando o resultado
    var result = personService.create(vo);
    
    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
}

     @Test
     void testCreateWithNullPerson() {
    // Verificando se uma exceção é lançada ao tentar criar uma pessoa com valor nulo
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
        personService.create(null);
    });
    
    String expectedMessage = "It is not allowed to persist a null object!";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
}

    @Test
    void testUpdate() throws Exception {
    // Criando uma instância de Person simulada para o teste
    Person entity = input.mockEntity(1); 
    
    // Criando uma instância de Person simulada após a persistência
    Person persisted = entity;
    persisted.setId(1L);
    
    // Criando uma instância de PersonVO simulada
    PersonVO vo = input.mockVO(1);
    vo.setKey(1L);
    
    // Configurando o comportamento do repositório mockado ao chamar os métodos findById e save
    when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    when(personRepository.save(entity)).thenReturn(persisted);
    
    // Chamando o método do serviço e verificando o resultado
    var result = personService.update(vo);
    
    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());
    
    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
}

     @Test
     void testUpdateWithNullPerson() {
    // Verificando se uma exceção é lançada ao tentar atualizar com uma pessoa nula
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
        personService.update(null);
    });
    
    String expectedMessage = "It is not allowed to persist a null object!";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
}

     @Test
     void testDelete() {
    // Criando uma instância de Person simulada para o teste
    Person entity = input.mockEntity(1); 
    entity.setId(1L);
    
    // Configurando o comportamento do repositório mockado ao chamar o método findById
    when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    
    // Chamando o método do serviço para deletar e verificando se não ocorrem exceções
    personService.delete(1L);
}

	
	@Test
    void testFindAll() {
    // Mockando uma lista de entidades Person para simular dados no repositório
    List<Person> list = input.mockEntityList(); 

    // Configurando o comportamento do repositório mockado ao chamar o método findAll
    when(personRepository.findAll()).thenReturn(list);
    
    // Chamando o serviço para obter a lista de pessoas
    var people = personService.findAll();
    
    // Verificando se a lista não é nula e possui o tamanho esperado
    assertNotNull(people);
    assertEquals(14, people.size());

    // Verificando informações específicas da primeira pessoa na lista (índice 1)
    var personOne = people.get(1);
    assertNotNull(personOne);
    assertNotNull(personOne.getKey());
    assertNotNull(personOne.getLinks());
    
    // Verificando detalhes específicos da primeira pessoa
    assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", personOne.getAddress());
    assertEquals("First Name Test1", personOne.getFirstName());
    assertEquals("Last Name Test1", personOne.getLastName());
    assertEquals("Female", personOne.getGender());

    // Repetindo o processo para a pessoa no índice 4
    var personFour = people.get(4);
    assertNotNull(personFour);
    assertNotNull(personFour.getKey());
    assertNotNull(personFour.getLinks());
    
    assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
    assertEquals("Addres Test4", personFour.getAddress());
    assertEquals("First Name Test4", personFour.getFirstName());
    assertEquals("Last Name Test4", personFour.getLastName());
    assertEquals("Male", personFour.getGender());

    // Repetindo o processo para a pessoa no índice 7
    var personSeven = people.get(7);
    assertNotNull(personSeven);
    assertNotNull(personSeven.getKey());
    assertNotNull(personSeven.getLinks());
    
    assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
    assertEquals("Addres Test7", personSeven.getAddress());
    assertEquals("First Name Test7", personSeven.getFirstName());
    assertEquals("Last Name Test7", personSeven.getLastName());

    /*Este conjunto de testes unitários é destinado à classe PersonService, que é responsável por fornecer serviços relacionados 
    à entidade Person. Abaixo está um resumo de cada teste:

    setUpMocks: Inicializa as instâncias necessárias para os testes, incluindo a criação de um objeto simulado (MockPerson)
    e a abertura dos mocks usando MockitoAnnotations.

   testFindById: Verifica se o método findById do serviço retorna corretamente os dados de uma pessoa com base no ID fornecido, 
   simulando o comportamento do repositório mockado.

   testCreate: Testa o método create do serviço, assegurando que ele persiste uma nova pessoa corretamente, 
   simulando a persistência no repositório mockado.

   testCreateWithNullPerson: Garante que uma exceção seja lançada quando o método create é chamado com um objeto nulo.

   testUpdate: Avalia se o método update atualiza corretamente uma pessoa com base em um objeto PersonVO fornecido,
   simulando o comportamento do repositório mockado.

   testUpdateWithNullPerson: Verifica se uma exceção é lançada quando o método update é chamado com um objeto nulo.

   testDelete: Assegura que o método delete do serviço funcione corretamente, deletando uma pessoa com base no ID fornecido, 
   simulando o comportamento do repositório mockado.

   Cada teste aborda diferentes cenários e operações do serviço PersonService, garantindo que ele se comporte conforme esperado
   em diversas situações. 

   }

}*/
