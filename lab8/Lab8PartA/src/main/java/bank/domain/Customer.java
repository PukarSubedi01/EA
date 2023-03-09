package bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	private String name;
	@Id
	@GeneratedValue
	private Long id;

	public Customer(String name) {
		this.name = name;
	}

	public Customer() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}
}
