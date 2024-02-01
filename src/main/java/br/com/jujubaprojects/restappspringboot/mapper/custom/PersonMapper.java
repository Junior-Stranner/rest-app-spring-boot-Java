package br.com.jujubaprojects.restappspringboot.mapper.custom;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;

@Service
public class PersonMapper {
    
    public PersonVO convertEntityToVO(Person person){
        PersonVO vo = new PersonVO();
        vo.setKey(person.getId());
        vo.setAddress(person.getAddress());
     //   vo.setBirthday(LocalDate.now());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;

    }

    public Person convertVoToEntity(PersonVO person){
        Person entity = new Person();
        entity.setId(person.getKey());
        entity.setAddress(person.getAddress());
    //    entity.se(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;

    }
}
