package com.health_sync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddMedicalRecordDto {
	@NotNull(message = "Patient id must be supplied")
	private long patient;
	@NotNull(message = "Doctor id must be supplied")
	private long doctor;
	@NotBlank(message = "Date must be supplied")
	private String date;
	@NotBlank(message = "Record must be supplied")
	private String record;
}
