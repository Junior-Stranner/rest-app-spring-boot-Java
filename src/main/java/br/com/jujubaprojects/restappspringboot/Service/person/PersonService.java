package br.com.jujubaprojects.restappspringboot.Service.person;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Controller.person.PersonController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.Repositories.PersonRepository;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;
import br.com.jujubaprojects.restappspringboot.exceptions.ResourceNotFoundException;
import br.com.jujubaprojects.restappspringboot.mapper.DozerMapper;
import br.com.jujubaprojects.restappspringboot.mapper.custom.PersonMapper;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(Person.class.getName());

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonMapper mapper;

    
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
	
		// Busca todas as entidades persistente no repositório
		List<Person> entities = personRepository.findAll();
	
		// Converte a lista de entidades persistentes para uma lista de PersonVO usando DozerMapper
		List<PersonVO> persons = DozerMapper.parseListObjects(entities, PersonVO.class);
	
		// Adiciona links de auto-relacionamento usando HATEOAS
		persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
	
		return persons;
	}
	

	public PersonVO findById(Long id) {
		logger.info("Finding one person by ID: {}");
	
		// Busca a entidade persistente no repositório
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No person found with ID: " + id));
	
		// Converte a entidade persistente para um objeto PersonVO usando DozerMapper
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
	
		// Adiciona um link de auto-relacionamento usando HATEOAS
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
	
		return vo;
	}
	
	
	public PersonVO create(PersonVO personVO) throws Exception {
		if (personVO == null) {
			throw new IllegalArgumentException("PersonVO cannot be null");
		}
	
		logger.info("Creating one person!");
	
		// Converte o objeto PersonVO para a entidade persistente Person usando DozerMapper
		Person entity = DozerMapper.parseObject(personVO, Person.class);
	
		// Salva a entidade no repositório e obtém a entidade persistente atualizada
		Person savedEntity = personRepository.save(entity);
	
		// Converte a entidade persistente atualizada de volta para um objeto PersonVO
		PersonVO vo = DozerMapper.parseObject(savedEntity, PersonVO.class);
	
		// Adiciona um link de auto-relacionamento usando HATEOAS
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
	
		return vo;
	}
	
	
	public PersonVO update(PersonVO personVO) throws Exception {
    if (personVO == null) {
        throw new IllegalArgumentException("PersonVO cannot be null");
    }

    logger.info("Updating one person!");

    // Busca a entidade persistente no repositório usando a chave
    Person entity = personRepository.findById(personVO.getKey())
            .orElseThrow(() -> new EntityNotFoundException("Person not found with key: " + personVO.getKey()));

    // Atualiza os atributos da entidade com os valores do objeto PersonVO
    entity.setFirstName(personVO.getFirstName());
    entity.setLastName(personVO.getLastName());
    entity.setAddress(personVO.getAddress());
    entity.setGender(personVO.getGender());

    // Salva a entidade atualizada no repositório
    Person updatedEntity = personRepository.save(entity);

    // Converte a entidade persistente atualizada de volta para um objeto PersonVO
    PersonVO updatedPersonVO = DozerMapper.parseObject(updatedEntity, PersonVO.class);

    // Adiciona um link de auto-relacionamento usando HATEOAS
    updatedPersonVO.add(linkTo(methodOn(PersonController.class).findById(updatedPersonVO.getKey())).withSelfRel());

    return updatedPersonVO;
}

	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
    // Busca a entidade persistente no repositório usando a chave
		Person person = this.personRepository.findById(id)
		.orElseThrow();
		
        this.personRepository.delete(person);

	}
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
//		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brasil " + i);
		person.setGender("Male");
		return person;
	}
}
