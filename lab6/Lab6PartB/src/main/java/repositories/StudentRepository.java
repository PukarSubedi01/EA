package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s join fetch s.department d join fetch s.grade where d.name = :department")
    List<Student> findStudentsByDepartment(String department);


    @Query("select distinct s from Student s join fetch s.grade g join fetch g.course c join fetch s.department where c.name = :course")
    List<Student> findStudentsByCourseName(String course);
}
