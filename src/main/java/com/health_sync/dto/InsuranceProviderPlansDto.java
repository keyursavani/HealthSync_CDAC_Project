package com.health_sync.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceProviderPlansDto {
    private Long id;
	private String companyName;
	private long contactNumber;
	private String email;
	private String address;
	private List<InsurancePlanProviderDto> insurancePlans = new ArrayList<>();
}
