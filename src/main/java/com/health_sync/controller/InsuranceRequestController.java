package com.health_sync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health_sync.dto.InsuranceRequestDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.services.InsuranceRequestServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insurance")
@AllArgsConstructor
public class InsuranceRequestController {

	private InsuranceRequestServices insuranceRequestServices;
	
	@PostMapping("/request")
	public ResponseEntity<?> addNewRequest(@RequestBody @Valid InsuranceRequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.value(),"Success",insuranceRequestServices.addNewRequest(dto)));
	}
}
