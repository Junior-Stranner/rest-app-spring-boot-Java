package br.com.judev.version2025ju.unittests.mapper;

import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.dto.v2.PersonDTOV2;
import br.com.judev.version2025ju.model.Person;
import br.com.judev.version2025ju.unittests.mapper.mocks.MockPerson;
import br.com.judev.version2025ju.dto.mapper.PersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperTest {

    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToDTOTest() {
        Person person = inputObject.mockEntity();
        PersonDTO dto = PersonMapper.toDTO(person);

        assertEquals(Long.valueOf(0L), dto.getId());
        assertEquals("First Name Test0", dto.getFirstName());
        assertEquals("Last Name Test0", dto.getLastName());
        assertEquals("Address Test0", dto.getAddress());
        assertEquals("Male", dto.getGender());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<Person> entityList = inputObject.mockEntityList();
        List<PersonDTO> dtoList = PersonMapper.toListDTO(entityList);

        assertEquals(14, dtoList.size());

        PersonDTO dto = dtoList.get(0);
        assertEquals(Long.valueOf(0L), dto.getId());
        assertEquals("First Name Test0", dto.getFirstName());
        assertEquals("Last Name Test0", dto.getLastName());
        assertEquals("Address Test0", dto.getAddress());
        assertEquals("Male", dto.getGender());
    }
}
