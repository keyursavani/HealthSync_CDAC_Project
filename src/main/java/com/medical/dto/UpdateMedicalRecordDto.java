package com.medical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMedicalRecordDto {
	@NotNull(message = "Doctor id must be supplied")
	 private Long doctorId;
	@NotBlank(message = "Date must be supplied")
	 private String date;
	@NotBlank(message = "Prescription must be supplied")
	 private String prescription;
}
