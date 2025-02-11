package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecordDoctorDto {
	private Long id;
	private PatientDto patient;
	private String date;
	private String record;
}
