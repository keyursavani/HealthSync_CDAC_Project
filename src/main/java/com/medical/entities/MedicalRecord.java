package com.medical.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.medical.dto.DoctorDto;
import com.medical.dto.PatientDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "medicalrecords")
@Getter
@Setter
@ToString
public class MedicalRecord {
	@Id
	private String id;
	private PatientDto patientDetails;
	private DoctorDto doctorDetails;
	private String date;
	private String prescription;
	private String image;
	private String imageName;
}
