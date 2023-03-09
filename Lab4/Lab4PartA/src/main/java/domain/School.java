package domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "schoolId")
    @MapKey(name = "studentId")
    private Map<Long, Student> student= new HashMap<Long, Student>();

    public School() {

    }

    public Long getId() {
        return id;
    }

    public School(Map<Long, Student> student) {
        this.student = student;
    }

    public Map<Long, Student> getStudent() {
        return student;
    }

    public void setStudent(Map<Long, Student> student) {
        this.student = student;
    }
}
