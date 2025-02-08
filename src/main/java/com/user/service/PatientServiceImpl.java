package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.PatientDto;
import com.user.dto.PatientInsuranceReqDto;
import com.user.dto.RegisterPatientDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.Patient;
import com.user.entities.UserRole;
import com.user.repository.DoctorRepository;
import com.user.repository.InsuranceProviderRepository;
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
	private DoctorRepository doctorRepository;
	private InsuranceProviderRepository insuranceProviderRepository;

	@Override
	public String registerPatient(RegisterPatientDto dto) {
		try {
			if(patientRepository.existsByEmail(dto.getEmail()) || doctorRepository.existsByEmail(dto.getEmail()) || insuranceProviderRepository.existsByEmail(dto.getEmail()))
				throw new HealthSynsException("This email is already registered. Please use a different email.");
			dto.setPassword(encoder.encode(dto.getPassword()));
			Patient patient = patientRepository.save(modelMapper.map(dto, Patient.class));
			return "You have successfully registered as a patient. Your unique ID is:  " + patient.getId();
		} catch (Exception e) {
			throw new HealthSynsException("This email is already registered. Please use a different email.");
		}
	}

	@Override
	public SignInResponseJwtDto patientLogin(SignInDto dto) {
		Patient patient = patientRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email or password. Please try again"));
		if (!encoder.matches(dto.getPassword(), patient.getPassword()))
			new HealthSynsException("Invalid email or password. Please try again");
		SignInResponseJwtDto pdto = modelMapper.map(patient, SignInResponseJwtDto.class);
		pdto.setRole(UserRole.PATIENT);
		return pdto;
	}

	@Override
	public PatientDto getPatientById(Long patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new HealthSynsException("Invalid Patient ID. Please enter a valid ID."));
		return modelMapper.map(patient, PatientDto.class);
	}

//	@Override
//	public List<MedicalRecordPatientDto> getMedicalRecord(Long patientId) {
//		List<MedicalRecordPatientDto> list = medicalRecordClient.getMedicalRecordByPatientId(patientId);
//		return list;
//	}

//	@Override
//	public PatientInsuranceReqDto getInsuranceRequests(Long patientId) {
//		if (!patientRepository.existsById(patientId))
//			throw new HealthSynsException("Invalid patient id");
//		Patient requests = patientRepository.getInsuranceRequests(patientId);
//		return modelMapper.map(requests, PatientInsuranceReqDto.class);
//	}

}