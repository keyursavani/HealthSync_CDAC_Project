package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.DoctorDto;
import com.user.dto.RegisterDoctorDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.Doctor;
import com.user.entities.UserRole;
import com.user.repository.DoctorRepository;
import com.user.repository.InsuranceProviderRepository;
import com.user.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private DoctorRepository doctorRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder encoder;
	private PatientRepository patientRepository;
	private InsuranceProviderRepository insuranceProviderRepository;

	@Override
	public String registerDoctor(RegisterDoctorDto dto) {
		try {
			if(doctorRepository.existsByEmail(dto.getEmail()) || patientRepository.existsByEmail(dto.getEmail()) || insuranceProviderRepository.existsByEmail(dto.getEmail()))
				throw new HealthSynsException("This email is already registered. Please use a different email.");
			dto.setPassword(encoder.encode(dto.getPassword()));
			Doctor doctor = doctorRepository.save(modelMapper.map(dto, Doctor.class));
			return "You have successfully registered as a doctor. Your unique ID is: " + doctor.getId();
		} catch (Exception e) {
			throw new HealthSynsException("Registration failed: This email or doctor ID is already registered.");
		}
	}

	@Override
	public SignInResponseJwtDto doctorLogin(SignInDto dto) {
		Doctor doctor = doctorRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email or password. Please try again"));
		if (!encoder.matches(dto.getPassword(), doctor.getPassword()))
			new HealthSynsException("Invalid email or password. Please try again");
		SignInResponseJwtDto sdto = modelMapper.map(doctor, SignInResponseJwtDto.class);
		sdto.setRole(UserRole.DOCTOR);
		return sdto;
	}

//	@Override
//	public List<MedicalRecordPatientDto> getMedicalRecord(Long doctorId) {
//		List<MedicalRecordPatientDto> list = medicalRecordClient.getMedicalRecordByDoctorId(doctorId);
//		return list;
//	}

	@Override
	public DoctorDto getDoctorById(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new HealthSynsException("Invalid Doctor ID. Please enter a valid ID."));
		DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
		return doctorDto;
	}

}