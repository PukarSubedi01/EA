package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue
	private int orderNumber;
	private String date;
	private String status;
	@ManyToOne
	private Customer customer;
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Collection<OrderLine> orderlines = new ArrayList<OrderLine>();

	public Order() {
	}

	public Order( String date, String status, Customer customer) {
		this.date = date;
		this.customer = customer;
		this.status = status;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	private void setOrderNumber(int ordernr) {
		this.orderNumber = orderNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<OrderLine> getOrderlines() {
		return Collections.unmodifiableCollection(orderlines);
	}

	public void setOrderlines(Collection<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderNumber=" + orderNumber +
				", date='" + date + '\'' +
				", status='" + status + '\'' +
				", customer=" + customer +
				", orderlines=" + orderlines +
				'}';
	}
}
