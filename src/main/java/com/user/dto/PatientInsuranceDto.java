package com.user.dto;

import com.user.entities.Gender;

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
//	private InsurancePlan insurance;
//	private InsuranceStatus status;
}
