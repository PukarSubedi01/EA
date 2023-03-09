package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passenger_id")
    private List<Flight> flightList = new ArrayList();

    public Passenger(String name, List<Flight> flight) {
        this.name = name;
        this.flightList = flight;
    }

    public Passenger() {

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

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}
