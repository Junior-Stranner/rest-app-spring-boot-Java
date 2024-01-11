package br.com.jujubaprojects.restappspringboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.restappspringboot.Model.Books.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
    
}
