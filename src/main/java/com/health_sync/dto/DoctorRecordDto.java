package com.health_sync.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoctorRecordDto {
	private Long id;
	private long doctorId;
	private String firstName;
	private String lastName;
    private String specialization;
	private long contactNumber;
	private String email;
	private String address;
	private List<MedicalRecordDoctorDto> medicalRecords = new ArrayList<>();
}
