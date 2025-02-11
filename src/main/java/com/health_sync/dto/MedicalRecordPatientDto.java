package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecordPatientDto {
	private Long id;
	private DoctorDto doctor;
	private String date;
	private String record;
}
