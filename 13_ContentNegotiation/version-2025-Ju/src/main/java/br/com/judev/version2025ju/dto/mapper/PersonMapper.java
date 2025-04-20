package br.com.judev.version2025ju.dto.mapper;

import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.dto.v2.PersonDTOV2;
import br.com.judev.version2025ju.model.Person;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static Person toEntity(PersonDTO dto){
        return new ModelMapper().map(dto, Person.class);
    }

    public static PersonDTO toDTO(Person entity){
        return new ModelMapper().map(entity, PersonDTO.class);
    }

    public static List<PersonDTO> toListDTO(List<Person> entitys){
     return entitys.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public static Person toEntity2(PersonDTOV2 dto){
        return new ModelMapper().map(dto, Person.class);
    }

    public static PersonDTOV2 toDTO2(Person entity){
        return new ModelMapper().map(entity, PersonDTOV2.class);
    }
}
