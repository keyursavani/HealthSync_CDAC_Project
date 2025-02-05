package com.medical.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import com.medical.custome_exception.HealthSynsException;
import com.medical.dto.AddMedicalRecordDto;
import com.medical.dto.DoctorDto;
import com.medical.dto.MedicalRecordDeleteRequestDto;
import com.medical.dto.MedicalRecordPatientDto;
import com.medical.dto.MedicalRecordPatientResponseDto;
import com.medical.dto.PatientDto;
import com.medical.dto.UpdateMedicalRecordDto;
import com.medical.entities.MedicalRecord;
import com.medical.repository.MedicalRecordRepository;
import com.medical.service.clients.UserServiceClient;

@Service

public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Value("${Image_Folder_Path}")
	private String imageFolderPath;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private UserServiceClient userServiceClient;

	@Override
	public String addMedicalRecord(MultipartFile file, AddMedicalRecordDto dto) {
		try {
			String filePath = "";

			DoctorDto doctorDto = userServiceClient.getDoctorById(dto.getDoctorId());
			PatientDto patientDto = userServiceClient.getPatientById(dto.getPatientId());
			MedicalRecord record = modelMapper.map(dto, MedicalRecord.class);

			if (!file.getOriginalFilename().isEmpty()) {
				filePath = imageFolderPath + file.getOriginalFilename();
				record.setImage(filePath);
			} else {
				record.setImage(null);
			}

			record.setDoctorDetails(doctorDto);
			record.setPatientDetails(patientDto);
			record = medicalRecordRepository.save(record);

			if (!file.getOriginalFilename().isEmpty())
				file.transferTo(new File(filePath));

			return "Medical record has been added successfully.";
		} catch (Exception e) {
			throw new HealthSynsException("Invalid data " + e.getMessage());
		}
	}

	@Override
	public MedicalRecordPatientResponseDto getMedicalRecordsByPatientId(Long patientId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MedicalRecord> response = medicalRecordRepository.findByPatientDetails_Id(patientId, pageable);
		List<MedicalRecord> medicalRecordList = response.getContent();
		List<MedicalRecordPatientDto> medicalRecordPatientDtos = medicalRecordList.stream()
				.map(medicalRecord -> modelMapper.map(medicalRecord, MedicalRecordPatientDto.class))
				.collect(Collectors.toList());
		MedicalRecordPatientResponseDto dto = new MedicalRecordPatientResponseDto();
		dto.setMedicalRecords(medicalRecordPatientDtos);
		dto.setTotalPages(response.getTotalPages());
		dto.setTotalElements(response.getTotalElements());
		dto.setCurrentPage(page);
		dto.setPageSize(size);
		return dto;
	}

	@Override
	public MedicalRecordPatientResponseDto getMedicalRecordsByDoctorId(Long doctorId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MedicalRecord> response = medicalRecordRepository.findByDoctorDetails_Id(doctorId, pageable);
		List<MedicalRecord> medicalRecordList = response.getContent();
		List<MedicalRecordPatientDto> medicalRecordPatientDtos = medicalRecordList.stream()
				.map(medicalRecord -> modelMapper.map(medicalRecord, MedicalRecordPatientDto.class))
				.collect(Collectors.toList());
		MedicalRecordPatientResponseDto dto = new MedicalRecordPatientResponseDto();
		dto.setMedicalRecords(medicalRecordPatientDtos);
		dto.setTotalPages(response.getTotalPages());
		dto.setTotalElements(response.getTotalElements());
		dto.setCurrentPage(page);
		dto.setPageSize(size);
		return dto;
	}

	@Override
	public String deleteMedicalRecord(String recordId, MedicalRecordDeleteRequestDto dto) {
		MedicalRecord record = medicalRecordRepository.findById(recordId)
				.orElseThrow(() -> new HealthSynsException("Invalid record id"));
		if (record.getDoctorDetails().getId() != dto.getDoctorId())
			throw new HealthSynsException("You don't have permission to delete this record.");
		medicalRecordRepository.deleteById(recordId);
		return "The record has been successfully deleted.";
	}

	@Override
	public String updateMedicalRecord(String recordId, MultipartFile file, UpdateMedicalRecordDto dto)
			throws IllegalStateException, IOException {
		String filePath = "";
		MedicalRecord record = medicalRecordRepository.findById(recordId).orElseThrow(() -> new HealthSynsException("Invalid record id"));
		if (record.getDoctorDetails().getId() != dto.getDoctorId())
			throw new HealthSynsException("You don't have permission to update this record.");
		record.setPrescription(dto.getPrescription());
		record.setDate(dto.getDate());
		if (!file.getOriginalFilename().isEmpty()) {
			filePath = imageFolderPath + file.getOriginalFilename();
			record.setImage(filePath);
		}
		record = medicalRecordRepository.save(record);
		if (!file.getOriginalFilename().isEmpty())
			file.transferTo(new File(filePath));
		return "The record has been successfully updated.";
	}

	@Override
	public MedicalRecordPatientDto getMedicalRecordById(String recordId) {
	       MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId).orElseThrow(()-> new HealthSynsException("Invalid record id"));
		return modelMapper.map(medicalRecord, MedicalRecordPatientDto.class);
	}

}
