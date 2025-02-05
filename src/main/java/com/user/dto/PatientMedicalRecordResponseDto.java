package com.user.dto;

import com.user.entities.Gender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientMedicalRecordResponseDto {
	private String _id;
	private Long id;
	private String firstName;
	private String lastName;
	private Gender geneder;
	private long contactNumber;
	private String email;
	private DoctorDto doctorDetails;
	private String date;
	private String prescription;
	private String image;
}
