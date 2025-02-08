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

import com.user.dto.RefreshJwtTokenDto;
import com.user.dto.RegisterInsurancesProviderDto;
import com.user.dto.ResponseDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.dto.SignoutRequestDto;
import com.user.service.InsuranceProviderService;
import com.user.service.LoginUserService;
import com.user.utils.JwtTokenUtils;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insuranceprovider")
@AllArgsConstructor
public class InsuranceProviderController {
	
  private InsuranceProviderService insuranceProviderService;
  private LoginUserService loginUserService;
	private JwtTokenUtils jwtTokenUtils;
	private ModelMapper modelMapper;
  
	@PostMapping("/signup")
	public ResponseEntity<?> registerInsuranceProvider(@RequestBody @Valid RegisterInsurancesProviderDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(),"Success",  insuranceProviderService.registerInsuranceprovider(dto)));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> insuranceproviderLogin(@RequestBody @Valid SignInDto dto) {
		SignInResponseJwtDto sdto = insuranceProviderService.insuranceproviderLogin(dto);
		loginUserService.addLoginUser(sdto);
		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success", ddto));
	}
	
	@GetMapping("/{pid}")
	public ResponseEntity<?> getInsuranceproviderById(@PathVariable Long pid){
		return ResponseEntity.ok(insuranceProviderService.getInsuranceproviderById(pid));
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshJwtToken(@RequestBody @Valid SignInDto dto){
		SignInResponseJwtDto sdto = insuranceProviderService.insuranceproviderLogin(dto);
		loginUserService.addLoginUser(sdto);
		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, sdto);
		RefreshJwtTokenDto rdto = modelMapper.map(ddto, RefreshJwtTokenDto.class);
		return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED.value(),"Success",rdto));
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> insuranceproviderLogOut(@RequestBody @Valid SignoutRequestDto dto){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",loginUserService.userSignOut(dto)));
	}
	
}
