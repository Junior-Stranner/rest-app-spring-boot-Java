package br.com.jujubaprojects.restappspringboot.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.Books.Book;
import br.com.jujubaprojects.restappspringboot.data.v1.BookVO;

@Service
public class BookMapper {
    
    public BookVO convertEntityToVO(Book book){
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
        
    }

    public Book convertVoToEntity(BookVO vo){
        Book entity = new Book();
        entity.setId(vo.getKey());
        entity.setAuthor(vo.getAuthor());
        entity.setLaunchDate(vo.getLaunchDate());
        entity.setPrice(vo.getPrice());
        entity.setTitle(vo.getTitle());
        return entity;

    }
}
