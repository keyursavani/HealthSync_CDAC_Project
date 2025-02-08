package com.user.service;

import com.user.dto.PatientDto;
import com.user.dto.PatientInsuranceReqDto;
import com.user.dto.RegisterPatientDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;

public interface PatientService {

	public String registerPatient(RegisterPatientDto dto);

	public SignInResponseJwtDto patientLogin(SignInDto dto);

//	public List<MedicalRecordPatientDto> getMedicalRecord(Long patientId);

//	public PatientInsuranceReqDto getInsuranceRequests(Long patientId);

	public PatientDto getPatientById(Long patientId);

}