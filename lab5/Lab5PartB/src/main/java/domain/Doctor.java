package domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Doctor {

	@Column(name = "type")
	private String doctorType;
	private String firstName;
	private String lastName;
	@Id
	@GeneratedValue
	private Long id;

	public Doctor() {
	}

	public Doctor(String doctorType, String firstName, String lastName) {
		this.doctorType = doctorType;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
}
