package domain;

import javax.persistence.Entity;

@Entity
public class Book extends Product{
    private String ISBN;


    public Book(String name, String description, double price, String ISBN) {
        super(name, description, price);
        this.ISBN = ISBN;
    }

    public Book() {

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
