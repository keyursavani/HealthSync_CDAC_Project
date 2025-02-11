package com.health_sync.services;

import com.health_sync.dto.AddMedicalRecordDto;

public interface MedicalRecordServices {
	public String addMedicalRecord(AddMedicalRecordDto dto);
// public List<MedicalRecord> getByDoctorId(Long doctorId);
}
