package br.com.judev.version2025ju.services;

import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.dto.v2.PersonDTOV2;
import br.com.judev.version2025ju.dto.mapper.PersonMapper;
import br.com.judev.version2025ju.exception.ResourceNotFoundException;
import br.com.judev.version2025ju.model.Person;

import java.util.List;
import java.util.logging.Logger;

import br.com.judev.version2025ju.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;


    public List<PersonDTO> findAll() {

        logger.info("Finding all People!");

        List<Person> persons = repository.findAll();
        return PersonMapper.toListDTO(persons);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return PersonMapper.toDTO(person);
    }

    public PersonDTO create(PersonDTO dto) {
        logger.info("Creating one Person!");
        Person entity = PersonMapper.toEntity(dto);
        Person newPerson = repository.save(entity);
        return PersonMapper.toDTO(newPerson);
    }
    public PersonDTOV2 createV2(PersonDTOV2 dto2) {
        logger.info("Creating one Person!");
        Person entity = PersonMapper.toEntity2(dto2);
        Person newPerson = repository.save(entity);
        return PersonMapper.toDTO2(newPerson);
    }

    public PersonDTO update(PersonDTO dto) {

        logger.info("Updating one Person!");
        Person entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        Person updatePerson =  repository.save(entity);
        return PersonMapper.toDTO(updatePerson);
    }

    public void delete(Long id) {

        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}
