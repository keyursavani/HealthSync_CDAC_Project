package com.health_sync.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health_sync.dto.InsuranceProviderPlanReqDto;
import com.health_sync.dto.InsuranceProviderPlansDto;
import com.health_sync.dto.PatientInsuranceReqDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterInsurancesProviderDto;
import com.health_sync.dto.RequestStatusChangeDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.security.JwtUtils;
import com.health_sync.services.InsuranceProviderServices;
import com.health_sync.services.InsuranceRequestServices;
import com.health_sync.services.LoginUserServices;
import com.health_sync.services.PatientServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insuranceprovider")
@AllArgsConstructor
public class InsuranceProviderController {

	private InsuranceProviderServices insuranceProviderServices;
	private LoginUserServices loginUserServices;
	private JwtUtils jwtUtils;
	private AuthenticationManager authMgr;
	private ModelMapper modelMapper;
	private PatientServices patientServices;
	private InsuranceRequestServices insuranceRequestServices;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerInsuranceProvider(@RequestBody @Valid RegisterInsurancesProviderDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.ACCEPTED.value(), insuranceProviderServices.registerInsuranceProvider(dto)));
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> doctorLogin(@RequestBody @Valid SignInDto dto) {

		SignInResponseJwtDto sdto = insuranceProviderServices.insuranceProviderLogin(dto);
		loginUserServices.addLoginUser(sdto);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());

		Authentication verifiedToken = authMgr.authenticate(token);
		sdto.setAuthToken(jwtUtils.generateJwtToken(verifiedToken));
		
		SignInResponseDto ddto = modelMapper.map(sdto, SignInResponseDto.class);

		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Login success", ddto));
	}
	
	@GetMapping("/plans/{providerId}")
	public ResponseEntity<?> getMyRecords(@PathVariable Long providerId) {
		InsuranceProviderPlansDto list = insuranceProviderServices.getMyInsurancePlan(providerId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success",list));
	}
	
	@GetMapping("/insurance/requests/{providerId}")
	public ResponseEntity<?> getProviderInsuranceRequest(@PathVariable Long providerId){
		InsuranceProviderPlanReqDto list = insuranceProviderServices.getInsuranceRequests(providerId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",list));
	}
	
	@GetMapping("/patient/records/{patientId}")
	public ResponseEntity<?> getPatientMediclRecords(@PathVariable Long patientId) {
		PatientRecordDto dto =  patientServices.getMedicalRecord(patientId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success",dto));
	}
	
	@PutMapping("/request/{requestId}")
  public ResponseEntity<?> changeRequestStatus(@RequestBody @Valid RequestStatusChangeDto dto, @PathVariable Long requestId){
	  return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success",insuranceRequestServices.changeRequestStatus(dto,requestId)));
  }
	
}
