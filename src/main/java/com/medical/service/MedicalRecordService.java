package com.medical.service;

import org.springframework.web.multipart.MultipartFile;

import com.medical.dto.AddMedicalRecordDto;

public interface MedicalRecordService {
	public String addMedicalRecord(MultipartFile file,AddMedicalRecordDto dto);
// public List<MedicalRecord> getByDoctorId(Long doctorId);
}
