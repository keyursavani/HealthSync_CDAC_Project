package com.health_sync.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health_sync.dto.DoctorRecordDto;
import com.health_sync.dto.InsuranceRequestPatientDto;
import com.health_sync.dto.PatientInsuranceReqDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterPatientDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.security.JwtUtils;
import com.health_sync.services.InsurancePlanServices;
import com.health_sync.services.LoginUserServices;
import com.health_sync.services.PatientServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
     
	private PatientServices patientServices;
	private LoginUserServices loginUserServices;
	private InsurancePlanServices insurancePlanServices;
	private JwtUtils jwtUtils;
	private AuthenticationManager authMgr;
	private ModelMapper modelMapper;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@RequestBody @Valid RegisterPatientDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.value(), patientServices.registerPatient(dto)));
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> patientLogin(@RequestBody @Valid SignInDto dto) {

		SignInResponseJwtDto sdto = patientServices.patientLogin(dto);
		loginUserServices.addLoginUser(sdto);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());

		Authentication verifiedToken = authMgr.authenticate(token);
		sdto.setAuthToken(jwtUtils.generateJwtToken(verifiedToken));
		SignInResponseDto ddto = modelMapper.map(sdto, SignInResponseDto.class);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Login success", ddto));
	}

	
	@GetMapping("/records/{patientId}")
	public ResponseEntity<?> getMyRecords(@PathVariable Long patientId) {
		PatientRecordDto dto =  patientServices.getMedicalRecord(patientId);
		return ResponseEntity
				.ok(new ResponseDto(HttpStatus.OK.value(), "Success",dto));
	}
	
	@GetMapping("/insurance/requests/{patientId}")
	public ResponseEntity<?> getPatientInsuranceRequest(@PathVariable Long patientId){
		PatientInsuranceReqDto list = patientServices.getInsuranceRequests(patientId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",list));
	}
	
	
	
	
	}
