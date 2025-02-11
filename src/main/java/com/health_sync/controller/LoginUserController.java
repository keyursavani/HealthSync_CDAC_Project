package com.health_sync.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health_sync.dto.ResponseDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.dto.SignUpDto;
import com.health_sync.security.JwtUtils;
import com.health_sync.services.LoginUserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginUserController {

	@Autowired
	private LoginUserServices userServices;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto dto) {
		System.out.println("Role :- "+dto.getRole());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),userServices.signUp(dto)));		
	}
	
	@GetMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto dto){
	    SignInResponseJwtDto user = userServices.signIn(dto);
	  
	 		UsernamePasswordAuthenticationToken token = 
	 				new UsernamePasswordAuthenticationToken(dto.getEmail(),
	 				dto.getPassword());
	 		
	 		Authentication verifiedToken = authMgr.authenticate(token);
	 	
	 	     user.setAuthToken(jwtUtils.generateJwtToken(verifiedToken));
	 	     
	 	    SignInResponseDto ddto = modelMapper.map(user, SignInResponseDto.class);
	 	     
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(),"Success",ddto));
	}
}
