package com.user.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "HealthSync-MedicalRecord")
public interface MedicalRecordClient {

//	@GetMapping("doctor/{doctorId}")
//	public DoctorDto getDoctorById(@PathVariable Long doctorId);
//
//	@GetMapping("patient/{patientId}")
//	public PatientDto getPatientById(@PathVariable Long patientId);
//
//	@GetMapping("user/{emailId}")
//	public LoginUserResponseDto findByEmail(@PathVariable String emailId);

//	@GetMapping("medicalrecord/patient/{patientId}")
//	public List<MedicalRecordPatientDto> getMedicalRecordByPatientId(@PathVariable Long patientId);
//	
//	@GetMapping("medicalrecord/doctor/{doctorId}")
//	public List<MedicalRecordPatientDto> getMedicalRecordByDoctorId(@PathVariable Long doctorId);
}
