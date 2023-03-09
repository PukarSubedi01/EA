package app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Student {
    @Id
    private String id;
    private String studentNumber;
    private String name;
    private String phone;

    private Address address;

    private List<Grade> grades;

    public Student(String studentNumber, String name, String phone, Address address, List<Grade> grades) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.grades = grades;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", grades=" + grades +
                '}';
    }
}
