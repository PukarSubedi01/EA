package domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "CD.findCdByArtist", query = "select c from CD as c where c.artist = :artist")
public class CD  extends Product{
    private String artist;

    public CD(String name, String description, double price, String artist) {
        super(name, description, price);
        this.artist = artist;
    }

    public CD() {

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CD{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", price=" + super.getDescription() +
                ", artist='" + artist + '\'' +
                '}';
    }
}
