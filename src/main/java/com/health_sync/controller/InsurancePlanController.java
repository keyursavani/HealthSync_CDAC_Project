package com.health_sync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health_sync.dto.AddInsurancePlanDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.services.InsurancePlanServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insuranceplan")
@AllArgsConstructor
public class InsurancePlanController {

	private InsurancePlanServices insurancePlanServices;

	@PostMapping("/add")
	public ResponseEntity<?> addInsurancePlan(@RequestBody @Valid AddInsurancePlanDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), insurancePlanServices.addNewInsurancePlan(dto)));
	}
	
	@GetMapping
	public ResponseEntity<?> showInsurancePlans(){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success", insurancePlanServices.showInsurancePlans()));
	}
}
