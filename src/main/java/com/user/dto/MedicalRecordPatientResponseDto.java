package com.user.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedicalRecordPatientResponseDto {

	List<MedicalRecordPatientDto> medicalRecords;
	private int totalPages;
	private long totalElements;
	private int currentPage;
	private int pageSize;
}
