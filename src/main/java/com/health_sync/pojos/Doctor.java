package com.health_sync.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@ToString(exclude = "medicalRecords")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30,nullable = false, unique = true)
	private long doctorId;
	@Column(nullable = false , length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column(length = 30, nullable = false)
    private String specialization;
	@Column(nullable = false , length = 12)
	private long contactNumber;
	@Column(nullable = false , length = 30, unique = true)
	private String email;
	@Column(nullable = false , length = 50)
	private String address;
	@Column(length = 300, nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
	private List<MedicalRecord> medicalRecords = new ArrayList<>();
	
	
	public void addMedicalRecords(MedicalRecord record) {
		this.medicalRecords.add(record);
		record.setDoctor(this);
	}

	public void removeMedicalRecords(MedicalRecord record) {
		this.medicalRecords.remove(record);
		record.setDoctor(null);
	}
}
