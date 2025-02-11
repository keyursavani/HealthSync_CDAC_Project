package com.health_sync.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class InsurancePlanProviderDto {
	private Long id;
	private String title;
	private long years;
	private long price;
	private String coverageDetails;
	private List<PatientInsuranceDto> patients = new ArrayList<>();
}
