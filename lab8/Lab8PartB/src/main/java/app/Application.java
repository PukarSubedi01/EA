package app;

import app.domain.Address;
import app.domain.Grade;
import app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import app.repositories.StudentRepository;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.deleteAll();
		Address address = new Address("1000N 4th street", "Fairfield","52557");
		List<Grade> grades = Arrays.asList(new Grade("MWA", "A"), new Grade("WAA", "B"));
		Student student = new Student("615433", "Pukar", "1234567890", address, grades);
		studentRepository.save(student);

		address = new Address("1000N 5th street", "Burlington","52457");
		grades = Arrays.asList(new Grade("MWA", "C"), new Grade("WAA", "A+"), new Grade("EA", "A"));
		student = new Student("615423", "Subedi", "3216549874", address, grades);

		studentRepository.save(student);

		System.out.println(studentRepository.findStudentsByName("Pukar"));
		System.out.println(studentRepository.findStudentsByPhone("3216549874"));
		System.out.println(studentRepository.findStudentsByCity("Burlington"));
		System.out.println(studentRepository.findStudentsByCourseName("EA"));
		System.out.println(studentRepository.findStudentsByCourseNameAndGrade("WAA", "A+"));

	}

}
