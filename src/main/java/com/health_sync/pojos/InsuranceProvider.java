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
@Table(name = "insuranceproviders")
@Getter
@Setter
@ToString(exclude = {"insurancePlans","insuranceRequests"})
public class InsuranceProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false , length = 30)
	private String companyName;
	@Column(nullable = false , length = 12)
	private long contactNumber;
	@Column(nullable = false , length = 30, unique = true)
	private String email;
	@Column(nullable = false , length = 50)
	private String address;
	@Column(length = 300, nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
	private List<InsurancePlan> insurancePlans = new ArrayList<>();
	
	@OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
	private List<InsuranceRequest> insuranceRequests = new ArrayList<>();
	
	public void addInsurancePlan(InsurancePlan plan) {
		this.insurancePlans.add(plan);
		plan.setProvider(this);
	}

	public void removeInsurancePlan(InsurancePlan plan) {
		this.insurancePlans.remove(plan);
		plan.setProvider(null);
	}
	
	public void addInsuranceRequest(InsuranceRequest request) {
		this.insuranceRequests.add(request);
		request.setProvider(this);
	}

	public void removeInsuranceRequest(InsuranceRequest request) {
		this.insuranceRequests.remove(request);
		request.setProvider(null);
	}
	
}
