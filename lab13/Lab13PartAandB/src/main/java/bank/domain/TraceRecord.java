package bank.domain;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date = LocalDate.now();
    private long accountnumber;
    private String operation;
    private double amount;
    public TraceRecord(){

    }

    public TraceRecord( long accountnumber, String operation, double amount) {
        this.accountnumber = accountnumber;
        this.operation = operation;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
}
