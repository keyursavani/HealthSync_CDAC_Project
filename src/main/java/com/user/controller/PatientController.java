package com.user.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.MedicalRecordPatientDto;
import com.user.dto.PatientInsuranceReqDto;
import com.user.dto.PatientRecordDto;
import com.user.dto.RefreshJwtTokenDto;
import com.user.dto.RegisterPatientDto;
import com.user.dto.ResponseDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.service.LoginUserService;
import com.user.service.PatientService;
import com.user.utils.JwtTokenUtils;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

	private PatientService patientService;
	private LoginUserService loginUserService;
	private JwtTokenUtils jwtTokenUtils;
	private ModelMapper modelMapper;

	@PostMapping("/signup")
	public ResponseEntity<?> registerPatient(@RequestBody @Valid RegisterPatientDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), patientService.registerPatient(dto)));
	}

	@GetMapping("/signin")
	public ResponseEntity<?> patientLogin(@RequestBody @Valid SignInDto dto) {

		SignInResponseJwtDto sdto = patientService.patientLogin(dto);
		loginUserService.addLoginUser(sdto);

		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Login success", ddto));
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable Long patientId){
		return ResponseEntity.ok(patientService.getPatientById(patientId));
	}
	
	@GetMapping("/refreshToken")
	public ResponseEntity<?> refreshJwtToken(@RequestBody @Valid SignInDto dto){
		SignInResponseJwtDto sdto = patientService.patientLogin(dto);
		loginUserService.addLoginUser(sdto);
		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		RefreshJwtTokenDto rdto = modelMapper.map(ddto, RefreshJwtTokenDto.class);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED.value(),"Success",rdto));
	}

//	@GetMapping("/records/{patientId}")
//	public ResponseEntity<?> getMyRecords(@PathVariable Long patientId) {
//		List<MedicalRecordPatientDto> list = patientService.getMedicalRecord(patientId);
//		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", list));
//	}

	@GetMapping("/insurance/requests/{patientId}")
	public ResponseEntity<?> getPatientInsuranceRequest(@PathVariable Long patientId) {
		PatientInsuranceReqDto list = patientService.getInsuranceRequests(patientId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", list));
	}

}
