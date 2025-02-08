package com.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.SignoutRequestDto;
import com.user.dto.RefreshJwtTokenDto;
import com.user.dto.RegisterDoctorDto;
import com.user.dto.ResponseDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.service.DoctorService;
import com.user.service.LoginUserService;
import com.user.utils.JwtTokenUtils;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

	private DoctorService doctorService;
	private LoginUserService loginUserService;
	private JwtTokenUtils jwtTokenUtils;
	private ModelMapper modelMapper;

	@PostMapping("/signup")
	public ResponseEntity<?> registerDoctor(@RequestBody @Valid RegisterDoctorDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(),"Success" ,doctorService.registerDoctor(dto)));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> doctorLogin(@RequestBody @Valid SignInDto dto) {
		SignInResponseJwtDto sdto = doctorService.doctorLogin(dto);
		loginUserService.addLoginUser(sdto);
		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", ddto));
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable Long doctorId){
		return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshJwtToken(@RequestBody @Valid SignInDto dto){
		SignInResponseJwtDto sdto = doctorService.doctorLogin(dto);
		loginUserService.addLoginUser(sdto);
		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		RefreshJwtTokenDto rdto = modelMapper.map(ddto, RefreshJwtTokenDto.class);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED.value(),"Success",rdto));
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> doctorLogOut(@RequestBody @Valid SignoutRequestDto dto){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",loginUserService.userSignOut(dto)));
	}
	
	

//	@GetMapping("/records/{doctorId}")
//	public ResponseEntity<?> getMyRecords(@PathVariable Long doctorId) {
//		DoctorRecordDto dto = doctorService.getMedicalRecord(doctorId);
//		return ResponseEntity.ok(dto);
//	}
	
//	@GetMapping("/records/{doctorId}")
//	public ResponseEntity<?> getMyRecords(@PathVariable Long doctorId) {
//		List<MedicalRecordPatientDto> list = doctorService.getMedicalRecord(doctorId);
//		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", list));
//	}

}