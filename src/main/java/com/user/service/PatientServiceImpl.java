package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.user.custome_exception.HealthSynsException;
import com.user.dto.DoctorDto;
import com.user.dto.PatientDto;
import com.user.dto.PatientInsuranceReqDto;
import com.user.dto.PatientRecordDto;
import com.user.dto.RegisterPatientDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.Doctor;
import com.user.entities.Patient;
import com.user.entities.UseRole;
import com.user.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	private PasswordEncoder encoder;
	private ModelMapper modelMapper;

	@Override
	public String registerPatient(RegisterPatientDto dto) {
		try {
			dto.setPassword(encoder.encode(dto.getPassword()));
			Patient patient = patientRepository.save(modelMapper.map(dto, Patient.class));
			return "Successfully register as a patient with id " + patient.getId();
		} catch (Exception e) {
			throw new HealthSynsException("Email is already exists");
		}
	}

	@Override
	public SignInResponseJwtDto patientLogin(SignInDto dto) {
		Patient patient = patientRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		if (!encoder.matches(dto.getPassword(), patient.getPassword()))
			new HealthSynsException("Invalid email and password");
		SignInResponseJwtDto pdto = modelMapper.map(patient, SignInResponseJwtDto.class);
		pdto.setRole(UseRole.PATIENT);
		return pdto;
	}
	
	
	@Override
	public PatientDto getPatientById(Long patientId) {
		Patient patient = patientRepository.findById(patientId)	.orElseThrow(() -> new HealthSynsException("Invalid patient id"));
		return modelMapper.map(patient, PatientDto.class);
	}

	@Override
	public PatientRecordDto getMedicalRecord(Long patientId) {
		if (!patientRepository.existsById(patientId))
			throw new HealthSynsException("Invalid patient patientId");
		Patient records = patientRepository.getMedicalRecords(patientId);
		if (records == null) {
			records = patientRepository.findById(patientId).orElseThrow(() -> new HealthSynsException("Invalid patient id"));
		}
		return modelMapper.map(records, PatientRecordDto.class);
	}

	@Override
	public PatientInsuranceReqDto getInsuranceRequests(Long patientId) {
		if (!patientRepository.existsById(patientId))
			throw new HealthSynsException("Invalid patient id");
		Patient requests = patientRepository.getInsuranceRequests(patientId);
		return modelMapper.map(requests, PatientInsuranceReqDto.class);
	}
	
	

}
