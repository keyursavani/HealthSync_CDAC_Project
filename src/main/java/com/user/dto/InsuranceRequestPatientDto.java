package com.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceRequestPatientDto {
	private Long Id;
	private InsuranceProviderPatientDto provider;
	private InsurancePlanPatientRequestDto plan;
//	private InsuranceStatus status;
}
