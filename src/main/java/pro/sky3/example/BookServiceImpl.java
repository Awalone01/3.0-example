package pro.sky3.example;

import org.springframework.stereotype.Service;

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
        return booksRepository.findByName(name);
    }

    public Collection<Book> findByAuthor (String author){
        return booksRepository.findBooksByAuthor(author);
    }

    public Collection<Book> findByAPart (String part){
        return booksRepository.findAllByNameContains(part);
    }
}
