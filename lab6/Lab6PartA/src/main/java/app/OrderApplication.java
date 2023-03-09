package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CDRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CDRepository cdRepository;

	@Autowired
	AddressRepository addressRepository;


	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book();
		book.setName("Hibernate 3");
		book.setDescription("Good book on Hibernate");
		book.setPrice(35.50);
		book.setISBN("123456");
		OrderLine ol1 = new OrderLine(2, book);

		CD cd = new CD();
		cd.setName("The best of Queen");
		cd.setDescription("Album from 1995");
		cd.setPrice(12.98);
		cd.setArtist("artist1");
		OrderLine ol2 = new OrderLine(4, cd);

		DVD dvd = new DVD();
		dvd.setName("The best of Queen");
		dvd.setDescription("Album from 1995");
		dvd.setPrice(12.98);
		dvd.setGenre("artist1");
		OrderLine ol3 = new OrderLine(4, dvd);

		Order o1 = new Order("234743", "12/10/06", "closed");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Address address1 = new Address("1000 N 4TH street","Amsterdam", "52557","USA");
//		Address address2 = new Address("1000 N 4TH street","Fairfield", "52557","Nepal");

		Customer c1 = new Customer("Frank", "Brown");
		c1.addOrder(o1);
		c1.setAddress(address1);
		o1.setCustomer(c1);

		orderRepository.save(o1);


		Optional<Order> orderOpt = orderRepository.findById(1L);
		Order order = orderOpt.get();
		printOrder(order);


		System.out.println("===================Fetched By queries with method names=================");
		System.out.println(customerRepository.findAll());
		System.out.println(cdRepository.findCDSByArtistAndAndPriceIsLessThan("artist1",13));

		System.out.println("===================Fetched By named queries=================");
		System.out.println(customerRepository.findByCountry("USA"));
		System.out.println(cdRepository.findCdByArtist("artist1"));

		System.out.println("===================Fetched By JPL queries=========================");
		List<OrderNumber> orderNumbers = orderRepository.findOrdernumbersByStatus();
		for (OrderNumber orderNumber:orderNumbers) {
			System.out.println(orderNumber.getOrdernr());
		}
		List<CustomerNames> customerNames = customerRepository.findCustomersByAddress();
		customerNames.forEach(customer -> System.out.println(customer.getFullName()));
		List<OrderNumber> orderNumbersByCustomerCity = orderRepository.findOrderNumbersFromCustomersByCity("Amsterdam");
		orderNumbersByCustomerCity.forEach(orders -> System.out.println(orders.getOrdernr()));
		System.out.println(cdRepository.findCDByArtistAndPrice("artist1",12));

		System.out.println("===================Fetched By native queries=================");
		System.out.println(addressRepository.findAddressByCity());
		System.out.println(cdRepository.findCDByArtistNativeQuery());


	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrdernr());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstname() + " "
				+ cust.getLastname());
		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}

	}

}
