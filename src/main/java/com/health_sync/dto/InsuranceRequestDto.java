package com.health_sync.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceRequestDto {
	@NotNull(message = "Patient id must be supplied")
	private Long patient;
	@NotNull(message = "Provider id must be supplied")
	private Long provider;
	@NotNull(message = "Plan id must be supplied")
	private Long plan;
}
