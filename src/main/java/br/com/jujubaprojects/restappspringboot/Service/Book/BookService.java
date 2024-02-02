package br.com.jujubaprojects.restappspringboot.Service.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Controller.Book.BookController;
import br.com.jujubaprojects.restappspringboot.Controller.person.PersonController;
import br.com.jujubaprojects.restappspringboot.Model.Books.Book;
import br.com.jujubaprojects.restappspringboot.Repositories.BookRepository;
import br.com.jujubaprojects.restappspringboot.data.v1.BookVO;
import br.com.jujubaprojects.restappspringboot.exceptions.ResourceNotFoundException;
import br.com.jujubaprojects.restappspringboot.mapper.DozerMapper;
import br.com.jujubaprojects.restappspringboot.mapper.custom.BookMapper;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
	BookMapper mapper;

    public List<BookVO> findAll() {
        logger.info("Finding all books!");
    
        // Busca todas as entidades persistente no repositório
        List<Book> entities = bookRepository.findAll();
    
        // Converte a lista de entidades persistentes para uma lista de BookVO usando DozerMapper
        List<BookVO> books = DozerMapper.parseListObjects(entities, BookVO.class);
    
        // Adiciona links de auto-relacionamento usando HATEOAS
        books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
    
        return books;
    }
    

    public BookVO findById(long id) {
        logger.info("Finding book by ID: {}");
    
        // Busca a entidade persistente no repositório
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No book found with ID: " + id));
    
        // Converte a entidade persistente para um objeto BookVO usando DozerMapper
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
    
        // Adiciona um link de auto-relacionamento usando HATEOAS
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
    
        return vo;
    }

    public BookVO create(BookVO bookVO) throws Exception{

        if (bookVO == null) throw new Exception();//throw new RequiredObjectIsNullException();

        logger.info("create a Book!");

        Book entity = DozerMapper.parseObject(bookVO, Book.class);

        Book savedEntity = bookRepository.save(entity);

        BookVO vo = DozerMapper.parseObject(savedEntity, BookVO.class);

        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());


        return vo;

    }
    

    public BookVO update(BookVO bookVO) throws Exception{

        if (bookVO == null) throw new Exception();//throw new RequiredObjectIsNullException();

        logger.info("update a Book!");

        Book entity = bookRepository.findById(bookVO.getKey()) 
        .orElseThrow(() -> new EntityNotFoundException("Book not found with key: " + bookVO.getKey()));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        Book updatedEntity = bookRepository.save(entity);

        BookVO updateBookVO = DozerMapper.parseObject(updatedEntity, BookVO.class);

        updateBookVO.add(linkTo(methodOn(PersonController.class).findById(updateBookVO.getKey())).withSelfRel());

        return updateBookVO;

    }

      public void  delete(Long id){
        
          logger.info("Deleting one Book!");
		
       // Busca a entidade persistente no repositório usando a chave
		Book book = this.bookRepository.findById(id)
		.orElseThrow();
		
        this.bookRepository.delete(book);

      }
}
