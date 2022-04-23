package pro.sky3.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky3.example.model.Book;

import java.util.Collection;
import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long>{

    Book findByNameIgnoreCase(String name);

    List<Book> findBooksByAuthorContainsIgnoreCase(String author);

    List<Book> findAllByNameContainsIgnoreCase(String part);

    List<Book> findsBooksByNameIgnoreCaseAndAuthor(String name, String author);

    List<Book> findsBooksByNameOrAuthor(String name, String author);

}
