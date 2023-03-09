package mvc;

import java.util.Collection;
import java.util.List;

public class Books {
    private Collection<Book> books;

    public Books(Collection<Book> books) {
        this.books = books;
    }
    public Collection<Book> getContacts() {
        return books;
    }
    public void setContacts(List<Book> books) {
        this.books = books;
    }

}
