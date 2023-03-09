package domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private int productNumber;
	private String name;
	private double price;

	public Product() {
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public int getProductNumber() {
		return productNumber;
	}

	private void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productNumber=" + productNumber +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
