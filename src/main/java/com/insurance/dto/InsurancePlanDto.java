package com.insurance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsurancePlanDto {
	@NotNull(message = "Provider id must be supplied")
	private Long providerId;
	@NotBlank(message = "Title must be supplied")
	private String title;
	@NotNull(message = "Years must be supplied")
	private long years;
	@NotNull(message = "Price must be supplied")
	private long price;
	@NotBlank(message = "Coveragedetails must be supplied")
	private String coverageDetails;
}
