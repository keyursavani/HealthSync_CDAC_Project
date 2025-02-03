package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.ResponseDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.dto.SignUpDto;
import com.user.service.LoginUserService;
import com.user.utils.JwtTokenUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginUserController {

	@Autowired
	private LoginUserService userServices;
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto dto) {
		System.out.println("Role :- " + dto.getRole());
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), userServices.signUp(dto)));
	}

	@GetMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto dto) {
		SignInResponseJwtDto user = userServices.signIn(dto);

		SignInResponseDto ddto = jwtTokenUtils.generateJwtToken(dto, user);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.value(), "Success", ddto));
	}
}
