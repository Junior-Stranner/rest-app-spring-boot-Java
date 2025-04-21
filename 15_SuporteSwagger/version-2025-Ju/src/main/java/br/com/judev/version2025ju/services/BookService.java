package br.com.judev.version2025ju.services;

import br.com.judev.version2025ju.controllers.BookController;
import br.com.judev.version2025ju.dto.mapper.BookMapper;
import br.com.judev.version2025ju.dto.v1.BookDTO;
import br.com.judev.version2025ju.exception.RequiredObjectIsNullException;
import br.com.judev.version2025ju.exception.ResourceNotFoundException;
import br.com.judev.version2025ju.model.Book;
import br.com.judev.version2025ju.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {


    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepository repository;


    public List<BookDTO> findAll() {

        logger.info("Finding all Book!");
        List<Book> books = repository.findAll();
        var bookDto = BookMapper.toListDTO(books);
        bookDto.forEach(this::addHateoasLinks);
        return bookDto;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = BookMapper.toDTO(entity);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one Book!");
        Book entity = BookMapper.toEntity(book);
        Book newBook = repository.save(entity);
        var dto = BookMapper.toDTO(newBook);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book!");
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        Book updateBook =  repository.save(entity);
        var dto = BookMapper.toDTO(updateBook);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one Book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
