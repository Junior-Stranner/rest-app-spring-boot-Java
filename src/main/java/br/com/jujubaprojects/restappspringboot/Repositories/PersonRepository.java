package br.com.jujubaprojects.restappspringboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jujubaprojects.restappspringboot.Model.person.Person;

@Repository
public interface PersonRepository extends JpaRepository <Person,Long>{
    
}
