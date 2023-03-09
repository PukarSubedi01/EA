package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String studentName;

    private String studentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stu_id")
    private List<Grade> grade;

    public Student(String studentName, String studentNumber, List<Grade> grade, Department department) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.department = department;
    }

    public Student() {

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Long getId() {
        return id;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", department=" + department +
                ", grade=" + grade +
                '}';
    }
}
