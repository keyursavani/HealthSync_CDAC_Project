package com.health_sync.services;

import com.health_sync.dto.InsuranceProviderPlanReqDto;
import com.health_sync.dto.InsuranceProviderPlansDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterInsurancesProviderDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;

public interface InsuranceProviderServices {
	public String registerInsuranceProvider(RegisterInsurancesProviderDto dto);
	 public SignInResponseJwtDto insuranceProviderLogin(SignInDto dto);
	public InsuranceProviderPlansDto getMyInsurancePlan(Long id);
	public InsuranceProviderPlanReqDto getInsuranceRequests(Long providerId);
	public PatientRecordDto getPatientMedicalRecord(Long patientId);
}
