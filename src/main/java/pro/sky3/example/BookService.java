package pro.sky3.example;

public interface BookService {

    Book createBook(Book book);

    Book findBook(long id);

    Book editBook(Book book);

    Book deleteBook(long id);
}
