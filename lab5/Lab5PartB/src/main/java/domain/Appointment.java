package domain;


import javax.persistence.*;

@Entity
public class Appointment {

	private String appdate;
	@ManyToOne
	@JoinColumn(name = "patient")
	private Patient patient;
	@Embedded
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "doctor")
	private Doctor doctor;
	@Id
	@GeneratedValue
	private Long id;

	public Appointment() {
	}

	public Appointment(String appdate, Patient patient, Payment payment,
			Doctor doctor) {
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}


	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Long getId() {
		return id;
	}
}
