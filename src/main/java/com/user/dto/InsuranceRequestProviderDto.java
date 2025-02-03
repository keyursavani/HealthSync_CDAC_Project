package com.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceRequestProviderDto {
	private Long Id;
	private PatientInsuranceRequestProviderDto patient;
	private InsurancePlanPatientRequestDto plan;
//	private InsuranceStatus status;
}
