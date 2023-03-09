package mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class BookController {

    private Map<String, Book> books = new HashMap<String, Book>();

    public BookController() {
        books.put("1234567489", new Book("1234567489", "Robert Ciadlini", "influence", 1000));
        books.put("789456123", new Book("789456123", "Random Person", "Random Title", 4500));
    }


    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        books.put(book.getTitle(), book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("/books")
    public ResponseEntity<?> updateBook( @RequestBody Book book) {
        System.out.println("Asdasdasdsad");
        books.put(book.getIsbn(), book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }


    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

        @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= "
                    + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        Books allBooks = new Books(books.values());
        return new ResponseEntity<Books>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/books/search/{author}")
    public ResponseEntity<?> searchBooks(@PathVariable String author){
        List<Book> searchResults = new ArrayList<>();
        for (Book book: books.values()){
            if(book.getAuthor().contains(author)){
                searchResults.add(book);
            }
        }

        return new ResponseEntity<List<Book>>(searchResults, HttpStatus.OK);
    }

}
