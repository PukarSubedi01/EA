package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private ProductDAO productDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		product();
	}

	void product(){
	productDao.clearDB();
	Product product = new Product("10", "Potato",10 );
	Supplier supplier = new Supplier(1, "Ujjwal", "1234567890");
	product.setSupplier(supplier);
	productDao.save(product);
	product = new Product("11", "Tomato", 100);
	supplier = new Supplier(2, "Pukar", "1234567891");
	product.setSupplier(supplier);
	productDao.save(product);
	System.out.println(productDao.getProductById("11"));
	System.out.println(productDao.getProductByName("Potato"));
	productDao.removeProduct("11");
	System.out.println("-----------All products ----------------");
	System.out.println(productDao.getAllProducts());
	}
}
