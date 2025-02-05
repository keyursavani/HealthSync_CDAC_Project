package com.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecordPatientDto {
	private String id;
	private DoctorDto doctorDetails;
	private PatientDto patientDetails;
	private String date;
	private String prescription;
	private String image;
}
