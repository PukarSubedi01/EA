package app;

import domain.Book;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.BookRepository;
import repositories.CustomerRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerrepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerrepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerrepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerrepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		book();
	}

	void book(){
		bookRepository.save(new Book("Influence", "12313","Robert Ciadini",100 ));
		bookRepository.save(new Book("The Art of Deception", "12123","Kevin D. Mitnick",100));
		bookRepository.save(new Book("Random book", "23123","Random Person",100 ));

		System.out.println("-----------All Books--------------------");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}


		bookRepository.updateBookTitle("Updated book", 8L);

		bookRepository.deleteById(6L);

		System.out.println("-----------All Books--------------------");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}

	}
}
