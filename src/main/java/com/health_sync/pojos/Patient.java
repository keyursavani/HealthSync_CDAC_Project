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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@Getter
@Setter
@ToString( exclude = {"medicalRecords","insuranceRequests"})
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false , length = 30)
	private String firstName;
	@Column(length = 30)
	private String lastName;
	@Column(nullable = false , length = 20)
	private String dob;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender geneder;
	@Column(nullable = false , length = 12)
	private long contactNumber;
	@Column(nullable = false , length = 30, unique = true)
	private String email;
	@Column(nullable = false , length = 50)
	private String address;
	@Column(length = 300, nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL  ,orphanRemoval = true)
	private List<MedicalRecord> medicalRecords = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="insurance_id")
	private InsurancePlan insurance;
	
	@Enumerated(EnumType.STRING)
	private InsuranceStatus status;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL  ,orphanRemoval = true)
	private List<InsuranceRequest> insuranceRequests = new ArrayList<>();
	
	
	
	public void addMedicalRecords(MedicalRecord record) {
		this.medicalRecords.add(record);
		record.setPatient(this);
	}

	public void removeMedicalRecords(MedicalRecord record) {
		this.medicalRecords.remove(record);
		record.setPatient(null);
	}
	
	public void addInsuranceRequest(InsuranceRequest request) {
		this.insuranceRequests.add(request);
		request.setPatient(this);
	}

	public void removeInsuranceRequest(InsuranceRequest request) {
		this.insuranceRequests.remove(request);
		request.setPatient(null);
	}
	
}
