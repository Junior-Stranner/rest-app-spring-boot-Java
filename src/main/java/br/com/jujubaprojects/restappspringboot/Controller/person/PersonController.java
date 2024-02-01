package br.com.jujubaprojects.restappspringboot.Controller.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.restappspringboot.Service.person.PersonService;
import br.com.jujubaprojects.restappspringboot.data.v1.PersonVO;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
	public PersonService personService;
	///private PersonServices service = new PersonServices();
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
	public List<PersonVO> findAll() {
		return personService.findAll();
	}
	
	@GetMapping(value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return personService.findById(id);
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })//Swagger exige isso  "produces = MediaType.APPLICATION_JSON_VALUE"
	public PersonVO  create(@RequestBody PersonVO  person) throws Exception {
		return personService.create(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
