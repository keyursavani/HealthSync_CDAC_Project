package com.health_sync.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "insuranceplans")
@Getter
@Setter
@ToString(exclude = {"provider","patients",})
public class InsurancePlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "provider_id",nullable = false)
	private InsuranceProvider provider;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column(nullable = false)
	private long years;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false, length = 200)
	private String coverageDetails;
	
	@OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
	private List<Patient> patients = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<InsuranceRequest> insuranceRequests = new ArrayList<>();
	
	public void addPatient(Patient patient) {
		this.patients.add(patient);
		patient.setInsurance(this);
	}

	public void removePatient(Patient patient) {
		this.patients.remove(patient);
		patient.setInsurance(null);
	}
	
	public void addInsuranceRequests(InsuranceRequest request) {
		this.insuranceRequests.add(request);
		request.setPlan(this);
	}

	public void removeInsuranceRequests(InsuranceRequest request) {
		this.insuranceRequests.remove(request);
		request.setPlan(null);
	}

}
