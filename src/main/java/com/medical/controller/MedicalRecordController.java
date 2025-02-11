package com.medical.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medical.dto.AddMedicalRecordDto;
import com.medical.dto.MedicalRecordDeleteRequestDto;
import com.medical.dto.ResponseDto;
import com.medical.dto.UpdateMedicalRecordDto;
import com.medical.service.MedicalRecordService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
	private MedicalRecordService medicalRecordService;

	@PostMapping("/add")
	public ResponseEntity<?> addMedicalRecord(@RequestPart(value="file" , required = false) MultipartFile file, @RequestPart("medicalRecord") @Valid AddMedicalRecordDto medicalRecord) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(),"Success" ,medicalRecordService.addMedicalRecord(file,medicalRecord)));
	}
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<?> getMedicalRecordByPatientId(@PathVariable Long patientId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",medicalRecordService.getMedicalRecordsByPatientId(patientId,page,size)));
	}
	
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<?> getMedicalRecordByDoctorId(@PathVariable Long doctorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",medicalRecordService.getMedicalRecordsByDoctorId(doctorId,page,size)));
	}
	
	@DeleteMapping("/delete/{recordId}")
	public ResponseEntity<?> deleteMedicalRecord(@PathVariable String recordId, @RequestBody @Valid MedicalRecordDeleteRequestDto dto){
	  return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",medicalRecordService.deleteMedicalRecord(recordId, dto)));	
	}
	
	@PatchMapping("/update/{recordId}")
	public ResponseEntity<?> updateMedicalRecord(@PathVariable String recordId, @RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("medicalRecord") @Valid UpdateMedicalRecordDto dto) throws IllegalStateException, IOException{
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(),"Success",medicalRecordService.updateMedicalRecord(recordId, file, dto)));	
	}
	
	@GetMapping("/{recordId}")
	public ResponseEntity<?> getMedicalRecordById(@PathVariable String recordId){
		return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Success",medicalRecordService.getMedicalRecordById(recordId)));
	}
	
	@GetMapping("/image/{name}")
	public ResponseEntity<?> downloadFile(@PathVariable String name) throws IOException{
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(medicalRecordService.downloadImage(name));		
	}
	
}
