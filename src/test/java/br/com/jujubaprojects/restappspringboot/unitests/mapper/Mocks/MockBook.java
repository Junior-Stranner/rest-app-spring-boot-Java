package br.com.jujubaprojects.restappspringboot.unitests.mapper.Mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.jujubaprojects.restappspringboot.Model.Books.Book;
import br.com.jujubaprojects.restappspringboot.data.v1.BookVO;

public class MockBook {
    

     public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(LocalDate.now());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setKey(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(LocalDate.now());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

}
