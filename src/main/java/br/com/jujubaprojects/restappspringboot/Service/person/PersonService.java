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
import br.com.jujubaprojects.restappspringboot.mapper.DozerMapper;
import br.com.jujubaprojects.restappspringboot.mapper.custom.PersonMapper;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(Person.class.getName());

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonMapper mapper;

    
    public List<PersonVO> findAll() {

		logger.info("Finding all people!");

	   List<PersonVO> persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		Person entity = personRepository.findById(id)
			.orElseThrow(); //.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {

		if (person == null) throw new Exception(); //throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonVO vo =  DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {

		if (person == null) throw new Exception();//throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");
		
		Person entity = personRepository.findById(person.getKey())
			.orElseThrow();

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo =  DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");

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
