package bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AccountEntry {
	private LocalDate date;
	private double amount;
	private String description;
	private String fromAccountNumber;
	private String fromPersonName;
	@Id
	@GeneratedValue
	private Long id;

	public AccountEntry() {
	}

	public AccountEntry(LocalDate date, double amount, String description, String fromAccountNumber, String fromPersonName) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.fromAccountNumber = fromAccountNumber;
		this.fromPersonName = fromPersonName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getFromPersonName() {
		return fromPersonName;
	}

	public void setFromPersonName(String fromPersonName) {
		this.fromPersonName = fromPersonName;
	}

	public Long getId() {
		return id;
	}
}
