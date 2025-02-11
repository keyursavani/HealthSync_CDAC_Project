package com.medical.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.medical.dto.AddMedicalRecordDto;
import com.medical.dto.MedicalRecordDeleteRequestDto;
import com.medical.dto.MedicalRecordPatientDto;
import com.medical.dto.MedicalRecordPatientResponseDto;
import com.medical.dto.UpdateMedicalRecordDto;

public interface MedicalRecordService {
	public String addMedicalRecord(MultipartFile file, AddMedicalRecordDto dto);

	public MedicalRecordPatientResponseDto getMedicalRecordsByPatientId(Long patientId, int page, int size);

	public MedicalRecordPatientResponseDto getMedicalRecordsByDoctorId(Long doctorId, int page, int size);

	public String deleteMedicalRecord(String recordId, MedicalRecordDeleteRequestDto dto);

	public String updateMedicalRecord(String recordId, MultipartFile file, UpdateMedicalRecordDto dto)
			throws IllegalStateException, IOException;
	
	public MedicalRecordPatientDto getMedicalRecordById(String recordId);
	
	public byte[] downloadImage(String imageName) throws IOException;
}
