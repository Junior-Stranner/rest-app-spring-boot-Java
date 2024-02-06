package br.com.jujubaprojects.restappspringboot.unitests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.mapper.DozerMapper;
import br.com.jujubaprojects.restappspringboot.unitests.mapper.Mocks.MockPerson;

public class DozerConverterTest {
    
   // Classe de teste para verificar o mapeamento de entidade e VO usando o DozerMapper
public class DozerMapperTest {
    
    // Objeto de entrada para os testes
    MockPerson inputObject;

    // Método executado antes de cada teste para configurar o objeto de entrada
    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    // Teste para verificar o mapeamento de entidade para VO
    @Test
    public void parseEntityToVOTest() {
        // Executa o mapeamento de entidade para VO usando o DozerMapper
        Person output = DozerMapper.parseObject(inputObject.mockEntity(), Person.class);
        
        // Verifica se os atributos mapeados correspondem aos valores esperados
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    // Teste para verificar o mapeamento de lista de entidades para lista de VOs
    @Test
    public void parseEntityListToVOListTest() {
        // Executa o mapeamento de lista de entidades para lista de VOs usando o DozerMapper
        List<Person> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), Person.class);
        
        // Verifica se os atributos mapeados correspondem aos valores esperados para várias posições na lista
        // Aqui estão os casos de teste para os elementos 0, 7 e 12 da lista
        Person outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    // Teste para verificar o mapeamento de VO para entidade
    @Test
    public void parseVOToEntityTest() {
        // Executa o mapeamento de VO para entidade usando o DozerMapper
        Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
        
        // Verifica se os atributos mapeados correspondem aos valores esperados
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    // Teste para verificar o mapeamento de lista de VOs para lista de entidades
    @Test
    public void parserVOListToEntityListTest() {
        // Executa o mapeamento de lista de VOs para lista de entidades usando o DozerMapper
        List<Person> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Person.class);
        
        // Verifica se os atributos mapeados correspondem aos valores esperados para várias posições na lista
        // Aqui estão os casos de teste para os elementos 0, 7 e 12 da lista
        Person outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
      }
   }
}