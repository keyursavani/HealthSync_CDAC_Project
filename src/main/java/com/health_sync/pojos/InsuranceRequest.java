package com.health_sync.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "insurancerequests")
@Getter
@Setter
@ToString
public class InsuranceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "provider_id",nullable = false)
	private InsuranceProvider provider;
	
	@ManyToOne
	@JoinColumn(name = "plan_id",nullable = false)
	private InsurancePlan plan;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private InsuranceStatus status;
}
