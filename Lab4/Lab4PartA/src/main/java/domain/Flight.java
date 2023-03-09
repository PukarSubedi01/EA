package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

  private String flightNumber;
  @Column(name = "departure")
  private String from;

  @Column(name = "destination")
  private String to;
  private LocalDate date;





    public Flight(String flightNumber, String from, String to, LocalDate date) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
