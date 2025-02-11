package com.health_sync.services;

import java.util.List;

import com.health_sync.dto.PatientInsuranceReqDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterPatientDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;

public interface PatientServices {

	public String registerPatient(RegisterPatientDto dto);
	  public SignInResponseJwtDto patientLogin(SignInDto dto);
	public PatientRecordDto getMedicalRecord(Long patientId);
	 public PatientInsuranceReqDto getInsuranceRequests(Long patientId);

}
