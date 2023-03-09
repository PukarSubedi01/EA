package app.hsql.domain;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastname;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private List<Pets> pet;

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public Person(String firstName, String lastname, List<Pets> pet) {
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

    public List<Pets> getPet() {
        return pet;
    }

    public void setPet(List<Pets> pet) {
        this.pet = pet;
    }
}
