package com.medical.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medical.dto.DoctorDto;
import com.medical.dto.PatientDto;
import com.medical.dto.ResponseDto;

@FeignClient(name = "HealthSync-UserService")
public interface UserServiceClient {

	@GetMapping("doctor/{doctorId}")
	public DoctorDto getDoctorById(@PathVariable Long doctorId);

	@GetMapping("patient/{patientId}")
	public PatientDto getPatientById(@PathVariable Long patientId);
}
