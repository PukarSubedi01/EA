package app.hsql.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pets {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    private int age;

    public Pets(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Pets() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }


}
