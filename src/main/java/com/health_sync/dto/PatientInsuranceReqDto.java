package com.health_sync.dto;

import java.util.ArrayList;
import java.util.List;

import com.health_sync.pojos.Gender;
import com.health_sync.pojos.InsurancePlan;
import com.health_sync.pojos.InsuranceStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientInsuranceReqDto {

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
	private List<InsuranceRequestPatientDto> insuranceRequests = new ArrayList<>();
}
