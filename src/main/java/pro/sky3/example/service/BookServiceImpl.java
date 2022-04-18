package pro.sky3.example.service;

import org.springframework.stereotype.Service;
import pro.sky3.example.repository.BooksRepository;
import pro.sky3.example.model.Book;

import java.util.Collection;

@Service
public class BookServiceImpl {

    private final BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book createBook(Book book) {
        return booksRepository.save(book);
    }

    public Book findBook(long id) {
        return booksRepository.findById(id).get();
    }

    public Book editBook(Book book) {
        return booksRepository.save(book);
    }

    public void deleteBook(long id) {
        booksRepository.deleteById(id);
    }

    public Collection<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book findByName(String name) {
        return booksRepository.findByNameIgnoreCase(name);
    }

    public Collection<Book> findByAuthor (String author){
        return booksRepository.findBooksByAuthorContainsIgnoreCase(author);
    }

    public Collection<Book> findByAPart (String part){
        return booksRepository.findAllByNameContainsIgnoreCase(part);
    }
}
