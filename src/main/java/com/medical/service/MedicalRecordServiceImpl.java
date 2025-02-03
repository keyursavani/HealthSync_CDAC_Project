package com.medical.service;

import java.io.File;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medical.custome_exception.HealthSynsException;
import com.medical.dto.AddMedicalRecordDto;
import com.medical.dto.DoctorDto;
import com.medical.dto.PatientDto;
import com.medical.dto.ResponseDto;
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
	private MedicalRecordRepository medicalRecordDao;
	@Autowired
	private UserServiceClient userServiceClient;

	@Override
	public String addMedicalRecord(MultipartFile file,AddMedicalRecordDto dto) {
		try {
			String filePath = imageFolderPath+file.getOriginalFilename();
			
			DoctorDto doctorDto = userServiceClient.getDoctorById(dto.getDoctorId());
			PatientDto patientDto = userServiceClient.getPatientById(dto.getPatientId());
		     System.out.println("Doctor dto :-"+doctorDto);
		     System.out.println("Patient dto :-"+patientDto);
			MedicalRecord record = modelMapper.map(dto, MedicalRecord.class);
			
			record.setImage(filePath);
			record.setDoctorDetails(doctorDto);
			record = medicalRecordDao.save(record);
			 file.transferTo(new File(filePath));
			return "Medical record added successfully";
		} catch (Exception e) {
			throw new HealthSynsException("Invalid data "+e.getMessage());
		}
	}

}
