package domain;

import javax.persistence.Entity;

@Entity
public class DVD extends Product{
    private String genre;

    public DVD(String name, String description, double price, String genre) {
        super(name, description, price);
        this.genre = genre;
    }

    public DVD() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
