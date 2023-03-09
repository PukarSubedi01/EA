package bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TraceRecord {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String transactionText;

    public TraceRecord(LocalDate date, String transactionText) {
        this.date = date;
        this.transactionText = transactionText;
    }

    public TraceRecord() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTransactionText() {
        return transactionText;
    }

    public void setTransactionText(String transactionText) {
        this.transactionText = transactionText;
    }

    public Long getId() {
        return id;
    }

}
