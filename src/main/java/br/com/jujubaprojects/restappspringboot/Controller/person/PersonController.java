package br.com.jujubaprojects.restappspringboot.Controller.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;
import br.com.jujubaprojects.restappspringboot.Service.person.PersonService;

@RestController
public class PersonController {
    
    @Autowired
    private PersonService personService;

}
