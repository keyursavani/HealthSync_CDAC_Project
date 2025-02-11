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
import com.health_sync.dto.RegisterDoctorDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.security.JwtUtils;
import com.health_sync.services.DoctorServices;
import com.health_sync.services.LoginUserServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

	private DoctorServices doctorServices;
	private LoginUserServices loginUserServices;
	private JwtUtils jwtUtils;
	private AuthenticationManager authMgr;
	private ModelMapper modelMapper;

	@PostMapping("/register")
	public ResponseEntity<?> registerDoctor(@RequestBody @Valid RegisterDoctorDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), doctorServices.registerDoctor(dto)));
	}

	@GetMapping("/login")
	public ResponseEntity<?> doctorLogin(@RequestBody @Valid SignInDto dto) {

		SignInResponseJwtDto sdto = doctorServices.doctorLogin(dto);
		loginUserServices.addLoginUser(sdto);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());

		Authentication verifiedToken = authMgr.authenticate(token);
		sdto.setAuthToken(jwtUtils.generateJwtToken(verifiedToken));
		 
		SignInResponseDto ddto = modelMapper.map(sdto, SignInResponseDto.class);
		
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Login success", ddto));
	}

	@GetMapping("/records/{doctorId}")
	public ResponseEntity<?> getMyRecords(@PathVariable Long doctorId) {
		DoctorRecordDto dto = doctorServices.getMedicalRecord(doctorId);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", dto));

	}

}
