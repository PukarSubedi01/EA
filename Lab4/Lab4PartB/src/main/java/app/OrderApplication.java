package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		performOperations();
	}

	public void performOperations() {
		System.out.println("-----------------------------");
		Address address = new Address("1000 N 4th Street","Fairfield","52557","USA");
		Customer customer = new Customer("Ujjwal","Humagain",address);
		System.out.println("Saving the customer with address");
		customerRepository.save(customer);
		System.out.println("-----------------------------");

		Product product = new Product("Pizza",54.00);
		System.out.println("Saving the product");
		productRepository.save(product);
		System.out.println("-----------------------------");

		Order order = new Order("2023-03-02","Placed",customer);
		customer.setTheOrders(Arrays.asList(order));
		OrderLine orderLine = new OrderLine(10,product);
		order.setOrderlines(Arrays.asList(orderLine));
		System.out.println("Saving the order with associated data");
		orderRepository.save(order);
		System.out.println("-----------------------------");

		System.out.println("Fetching all the orders with associated data");
		System.out.println(orderRepository.findAll());

	}
}
