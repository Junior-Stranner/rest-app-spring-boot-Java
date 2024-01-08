package br.com.jujubaprojects.restappspringboot.Service.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.Repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(Person.class.getName());

	@Autowired
	PersonRepository personRepository;

    
    	public List<Person> findAll() {
		logger.info("Finding all people!");
		return this.personRepository.findAll();
	}

	public Person findById(Long id) {
		
		logger.info("Finding one person!");
	
		return this.personRepository.findById(id)
		.orElseThrow();
	}
	
	public Person create(Person person) {

		logger.info("Creating one person!");
		
		return this.personRepository.save(person);
	}
	
	public Person update(Person person) {

		this.personRepository.findById(person.getId())
		.orElseThrow();
		
		logger.info("Updating one person!");

	//	person.setId(counter.incrementAndGet());
	    person.setFirstName(person.getFirstName());
		person.setLastName(person.getLastName());
		person.setAddress(person.getAddress());
		person.setGender(person.getGender());

		return this.personRepository.save(person);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");

		Person pessoa = this.personRepository.findById(id)
		.orElseThrow();
		
        this.personRepository.delete(pessoa);

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
