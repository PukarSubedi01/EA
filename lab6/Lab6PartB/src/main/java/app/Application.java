package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CourseRepository;
import repositories.DepartmentRepository;
import repositories.GradeRepository;
import repositories.StudentRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	GradeRepository gradeRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		insertData();

		System.out.println(studentRepository.findStudentsByDepartment("compro"));
		System.out.println(studentRepository.findStudentsByCourseName("FPP"));
	}

	void insertData(){
		Department department = new Department("compro");
		departmentRepository.save(department);
		List<Course> course = Arrays.asList(new Course("EA"),
				new Course("WAP"),
				new Course("Algorithm"),
				new Course("MWA"),
				new Course("FPP"));
		courseRepository.saveAll(course);

		List<Grade> pukarGrades = Arrays.asList(new Grade("A", course.get(0)),
				new Grade("A-", course.get(1)),
				new Grade("B", course.get(2)),
				new Grade("A", course.get(3)),
				new Grade("A", course.get(4))
		);

		List<Grade> sanilGrades = Arrays.asList(new Grade("B", course.get(0)),
				new Grade("A-", course.get(1)),
				new Grade("B", course.get(2)),
				new Grade("C", course.get(3))
		);


		List<Student> students = Arrays.asList(new Student("Pukar","615433",pukarGrades,department),
				new Student("Sanil", "615432", sanilGrades, department));

		studentRepository.saveAll(students);
	}

}
