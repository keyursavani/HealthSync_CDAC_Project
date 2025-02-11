package com.health_sync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.health_sync.dto.AddMedicalRecordDto;
import com.health_sync.dto.ResponseDto;
import com.health_sync.services.MedicalRecordServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
	private MedicalRecordServices medicalRecordServices;

	@PostMapping("/add")
	public ResponseEntity<?> addMedicalRecord(@RequestBody @Valid AddMedicalRecordDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), medicalRecordServices.addMedicalRecord(dto)));
	}
}
