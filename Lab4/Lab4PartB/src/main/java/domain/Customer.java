package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Collection<Order> theOrders = new ArrayList<Order>();

	public Customer() {
	}

	public Customer(String firstname, String lastname,Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setTheOrders(Collection<Order> theOrders) {
		this.theOrders = theOrders;
	}

	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(theOrders);
	}

	
	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order); 
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", address=" + address +
				'}';
	}
}
