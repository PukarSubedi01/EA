package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Publisher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Publisher() {

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher(String name) {
        this.name = name;
    }
}
