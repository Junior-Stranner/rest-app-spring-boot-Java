package br.com.judev.version2025ju.service;

import br.com.judev.version2025ju.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private static  final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding all People!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Junior");
        person.setLastName("Junior");
        person.setAddress("Dubai - mak street");
        person.setGender("Male");
        return person;
    }


    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create(Person person) {

        logger.info("Creating one Person!");

        return person;
    }

    public Person update(Person person) {

        logger.info("Updating one Person!");

        return person;
    }

    public void delete(String id) {

        logger.info("Deleting one Person!");

    }


    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname " + i);
        person.setLastName("Lastname " + i);
        person.setAddress("Some Address in Brasil");
        person.setGender("Male");
        return person;
    }


}
