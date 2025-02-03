package com.user.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceProviderPlanReqDto {

	private Long id;
	private String companyName;
	private long contactNumber;
	private String email;
	private String address;
	// InsuranceRequest
	private List<InsuranceRequestProviderDto> insuranceRequests = new ArrayList<>();
}
