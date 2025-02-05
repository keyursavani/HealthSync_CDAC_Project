package com.user.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.MedicalRecordPatientDto;
import com.user.dto.PatientDto;
import com.user.dto.PatientInsuranceReqDto;
import com.user.dto.PatientMedicalRecordResponseDto;
import com.user.dto.RegisterPatientDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.CompositeKey;
import com.user.entities.Patient;
import com.user.entities.UserRole;
import com.user.repository.PatientRepository;
import com.user.service.client.MedicalRecordClient;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	private PasswordEncoder encoder;
	private ModelMapper modelMapper;
	private MedicalRecordClient medicalRecordClient;

	@Override
	public String registerPatient(RegisterPatientDto dto) {
		try {
			dto.setPassword(encoder.encode(dto.getPassword()));
			Patient patient = patientRepository.save(modelMapper.map(dto, Patient.class));
			return "Successfully register as a patient with id ";
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
		CompositeKey key = new CompositeKey();
		key.setEmail(patient.getEmail());
		key.setRole(UserRole.PATIENT);
		pdto.setId(key);
		return pdto;
	}
	
	
	@Override
	public PatientDto getPatientById(Long patientId) {
		Patient patient = patientRepository.findById(patientId)	.orElseThrow(() -> new HealthSynsException("Invalid patient id"));
		return modelMapper.map(patient, PatientDto.class);
	}

//	@Override
//	public List<MedicalRecordPatientDto> getMedicalRecord(Long patientId) {
//		List<MedicalRecordPatientDto> list = medicalRecordClient.getMedicalRecordByPatientId(patientId);
//		return list;
//	}

	@Override
	public PatientInsuranceReqDto getInsuranceRequests(Long patientId) {
		if (!patientRepository.existsById(patientId))
			throw new HealthSynsException("Invalid patient id");
		Patient requests = patientRepository.getInsuranceRequests(patientId);
		return modelMapper.map(requests, PatientInsuranceReqDto.class);
	}
	
	

}
