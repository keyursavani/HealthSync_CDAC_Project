package com.insurance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.InsurancePlanDto;
import com.insurance.dto.ResponseDto;
import com.insurance.service.InsurancePlanService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insuranceplan")
@AllArgsConstructor
public class InsurancePlanController {

	private InsurancePlanService insurancePlanService;

	@PostMapping("/add")
	public ResponseEntity<?> addInsurancePlan(@RequestBody @Valid InsurancePlanDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto(HttpStatus.CREATED.value(), "Success", insurancePlanService.addInsuranceplan(dto)));
	}

}
