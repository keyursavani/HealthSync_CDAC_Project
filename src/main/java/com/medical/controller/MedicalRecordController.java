package com.medical.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medical.dto.AddMedicalRecordDto;
import com.medical.dto.ResponseDto;
import com.medical.service.MedicalRecordService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
	private MedicalRecordService medicalRecordService;

	@PostMapping("/add")
	public ResponseEntity<?> addMedicalRecord(@RequestPart("file") MultipartFile file, @RequestPart("medicalRecord") @Valid AddMedicalRecordDto medicalRecord) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), medicalRecordService.addMedicalRecord(file,medicalRecord)));
	}
}
