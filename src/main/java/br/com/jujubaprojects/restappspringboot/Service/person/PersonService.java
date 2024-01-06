package br.com.jujubaprojects.restappspringboot.Service.person;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(Person.class.getName());
}
