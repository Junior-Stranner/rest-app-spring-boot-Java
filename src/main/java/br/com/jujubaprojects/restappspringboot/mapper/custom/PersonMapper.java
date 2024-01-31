package br.com.jujubaprojects.restappspringboot.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;

@Service
public class PersonMapper {
    
    public PersonVO convertEntityToVO(Person person){
        PersonVO vo = new PersonVO();
        vo.setKey(person.getKey());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;

    }

    public Person convertVoToEntity(PersonVO person){
        Person entity = new Person();
        entity.setKey(person.getKey());
        entity.setAddress(person.getAddress());
    //    entity.se(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;

    }
}
