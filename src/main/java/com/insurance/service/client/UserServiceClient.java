package com.insurance.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.insurance.dto.InsuranceproviderDto;

@FeignClient(name = "HealthSync-UserService")
public interface UserServiceClient {

//	@GetMapping("doctor/{doctorId}")
//	public DoctorDto getDoctorById(@PathVariable Long doctorId);
//
//	@GetMapping("patient/{patientId}")
//	public PatientDto getPatientById(@PathVariable Long patientId);
//
//	@GetMapping("user/{emailId}")
//	public LoginUserResponseDto findByEmail(@PathVariable String emailId);
	
	@GetMapping("insuranceprovider/{pid}")
	public InsuranceproviderDto getInsuranceproviderById(@PathVariable Long pid);
}
