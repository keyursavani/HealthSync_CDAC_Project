package com.health_sync.dto;

import com.health_sync.pojos.InsuranceStatus;

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
	private InsuranceStatus status;
}
