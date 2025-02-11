package com.health_sync.dto;

import com.health_sync.pojos.Gender;
import com.health_sync.pojos.InsurancePlan;
import com.health_sync.pojos.InsuranceStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientInsuranceDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String dob;
	private Gender geneder;
	private long contactNumber;
	private String email;
	private String address;
	private InsurancePlan insurance;
	private InsuranceStatus status;
}
