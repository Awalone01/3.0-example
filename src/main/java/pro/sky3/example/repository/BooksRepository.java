package pro.sky3.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky3.example.model.Book;

import java.util.Collection;

public interface BooksRepository extends JpaRepository<Book, Long>{

    Book findByNameIgnoreCase(String name);

    Collection<Book> findBooksByAuthorContainsIgnoreCase(String author);

    Collection<Book> findAllByNameContainsIgnoreCase(String part);

    Collection<Book> findsBooksByNameIgnoreCaseAndAuthor(String name, String author);

    Collection<Book> findsBooksByNameOrAuthor(String name, String author);

}
