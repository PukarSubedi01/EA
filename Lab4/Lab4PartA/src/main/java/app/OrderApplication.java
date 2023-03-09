package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.BookRepository;
import repositories.DepartmentRepository;
import repositories.PassengerRepository;
import repositories.SchoolRepository;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class OrderApplication implements CommandLineRunner{
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveAndGetDepartment();
		saveBook();
		savePassenger();
		saveSchool();

	}

	void saveAndGetDepartment(){
		Department department = new Department("IT");
		List<Employee> employee = new ArrayList<>();

		employee.add(new Employee("1234","John", department));
		employee.add(new Employee("12", "DOE", department));
		department.setEmployeeList(employee);
		departmentRepository.save(department);
		System.out.println(departmentRepository.findAll());

	}

	void saveBook(){
		Publisher publisher = new Publisher("ekta publications");
		Book book = new Book("123","ek chihan","Hridaya chandra pradhan", publisher);
		bookRepository.save(book);
		System.out.println(bookRepository.findAll());

	}

	void savePassenger(){
		List<Flight> flights= Arrays.asList(new Flight("123","asd","fgf", LocalDate.now()), new Flight("345","tt","sdf", LocalDate.now()));
		Passenger passenger = new Passenger("Hari", flights);
		passengerRepository.save(passenger);
		System.out.println(passengerRepository.findAll());


	}

	void saveSchool(){
		Map<Long,Student> student = new HashMap<>();

		student.put(8L,new Student(8L,"Pukar", "Subedi"));
		student.put(9L,new Student(9L,"Punjan", "Subedi"));
		School school = new School(student);
		schoolRepository.save(school);
		System.out.println(schoolRepository.findAll());

	}
}
