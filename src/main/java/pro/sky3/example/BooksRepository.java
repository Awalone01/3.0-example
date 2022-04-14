package pro.sky3.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BooksRepository extends JpaRepository<Book, Long>{

    Book findByName(String name);

    Collection<Book> findBooksByAuthor(String author);

    Collection<Book> findAllByNameContains(String part);

}
