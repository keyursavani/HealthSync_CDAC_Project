package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceProviderPatientDto {
	private Long id;
	private String companyName;
	private long contactNumber;
	private String email;
	private String address;
}
