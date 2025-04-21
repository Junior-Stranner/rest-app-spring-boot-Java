package br.com.judev.version2025ju.repository;

import br.com.judev.version2025ju.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
