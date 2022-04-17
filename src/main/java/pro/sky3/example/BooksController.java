package pro.sky3.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BookServiceImpl bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity findBooks(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String author,
                                    @RequestParam(required = false) String part) {
        if (name != null && name.isBlank()) {
           return ResponseEntity.ok(bookService.findByName(name));
        }
        if (author != null && author.isBlank()) {
            return ResponseEntity.ok(bookService.findByAuthor(author));
        }
        if (part != null && part.isBlank()) {
            return ResponseEntity.ok(bookService.findByAPart(part));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
