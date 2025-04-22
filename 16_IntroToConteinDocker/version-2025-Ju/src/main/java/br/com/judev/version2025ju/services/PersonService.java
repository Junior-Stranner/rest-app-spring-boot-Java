package br.com.judev.version2025ju.services;

import br.com.judev.version2025ju.controllers.PersonController;
import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.dto.v2.PersonDTOV2;
import br.com.judev.version2025ju.dto.mapper.PersonMapper;
import br.com.judev.version2025ju.exception.ResourceNotFoundException;
import br.com.judev.version2025ju.model.Person;

import java.util.List;
import java.util.logging.Logger;

import br.com.judev.version2025ju.repository.PersonRepository;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

//    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");

        List<Person> persons = repository.findAll();
        var personsDto = PersonMapper.toListDTO(persons);
        personsDto.forEach(this::addHateoasLinks);
        return personsDto;
    }


    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = PersonMapper.toDTO(person);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating one Person!");
        Person entity = PersonMapper.toEntity(personDTO);
        Person newPerson = repository.save(entity);
        var dto = PersonMapper.toDTO(newPerson);
        addHateoasLinks(dto);
        return dto;

    }
    public PersonDTOV2 createV2(PersonDTOV2 dto2) {
        logger.info("Creating one Person!");
        Person entity = PersonMapper.toEntity2(dto2);
        Person newPerson = repository.save(entity);
        return PersonMapper.toDTO2(newPerson);
    }

    public PersonDTO update(PersonDTO personDTO) {

        logger.info("Updating one Person!");
        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        Person updatePerson =  repository.save(entity);
        var dto = PersonMapper.toDTO(updatePerson);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }


}
