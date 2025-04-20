package br.com.judev.version2025ju.controllers;

import br.com.judev.version2025ju.controllers.documentation.PersonDocumentationController;
import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.dto.v2.PersonDTOV2;
import br.com.judev.version2025ju.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/person")
public class PersonController implements PersonDocumentationController {
    @Autowired
    private PersonService service;
    // private PersonServices service = new PersonServices();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE} )
    public List<PersonDTO> findAll() {
        return service.findAll();
    }


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO findById(@PathVariable("id") Long id) {
        var personDTO = service.findById(id);
        personDTO.setBirthday(new Date());
    //    personDTO.setPhoneNumber("+55 (34) 987654321");
        personDTO.setPhoneNumber("");
        personDTO.setLastName(null);
        personDTO.setSensitiveData("Foo Bar");
        return personDTO;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO create(@RequestBody  PersonDTO dto) {
        return service.create(dto);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO update(@RequestBody PersonDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
