package br.com.judev.version2025ju.repository;

import br.com.judev.version2025ju.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
