package app.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PersonMongo {
    @Id
    private String id;

    private String firstName;
    private String lastname;


    private List<PetsMongo> pet;

    public PersonMongo() {

    }

    public String getId() {
        return id;
    }

    public PersonMongo(String firstName, String lastname, List<PetsMongo> pet) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.pet = pet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<PetsMongo> getPet() {
        return pet;
    }

    public void setPet(List<PetsMongo> pet) {
        this.pet = pet;
    }
}
