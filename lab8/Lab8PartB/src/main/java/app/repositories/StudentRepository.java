package app.repositories;

import app.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
    List<Student> findStudentsByName(String name);

    Student findStudentsByPhone(String phoneNumber);

    @Query("{'address.city' : :#{#city}}")
    List<Student> findStudentsByCity(String city);

    @Query("{'grades.courseName' : :#{#courseName}}")
    List<Student> findStudentsByCourseName(String courseName);

    @Query(("{ 'grades': { $elemMatch: { 'courseName' : :#{#courseName} , 'grade': :#{#grade}} }}"))
    List<Student> findStudentsByCourseNameAndGrade(String courseName, String grade);

}
