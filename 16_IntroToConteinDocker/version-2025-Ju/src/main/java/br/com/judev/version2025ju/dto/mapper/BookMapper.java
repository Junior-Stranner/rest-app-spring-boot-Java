package br.com.judev.version2025ju.dto.mapper;

import br.com.judev.version2025ju.dto.v1.BookDTO;
import br.com.judev.version2025ju.dto.v1.PersonDTO;
import br.com.judev.version2025ju.model.Book;
import br.com.judev.version2025ju.model.Person;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static Book toEntity(BookDTO dto){
        return new ModelMapper().map(dto, Book.class);
    }

    public static BookDTO toDTO(Book entity){
        return new ModelMapper().map(entity, BookDTO.class);
    }

    public static List<BookDTO> toListDTO(List<Book> entitys){
        return entitys.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
