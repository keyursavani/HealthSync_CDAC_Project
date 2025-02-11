package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsurancePlanPatientRequestDto {
	private Long id;
	private String title;
	private long years;
	private long price;
	private String coverageDetails;
}
